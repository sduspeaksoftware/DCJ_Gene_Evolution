package usingGRB;

import gurobi.*;

public class grbilp {
	//并不知道为什么k无法导入，应当为v1=2k；
	static int V1 ;
	static int V2 ;
	static int[][] edges = new int[V1][V2];
	
	public static void init() {
		for(int i=0; i<V1; i++)
			for(int j=0; j<V2; j++)
				edges[i][j] = 0;
		for(int i=0; i<V1-1; i++) {
			edges[i][i+1] = 1;
			i++;
		}
		for(int i=1; i<V1; i++) {
			edges[i][i-1] = 1;
			i++;
		}
		edges[0][V1/2]=edges[V1/2-1][V1-1]=1;
	}

	public static void Grbilp(int k) {
		// TODO Auto-generated method stub
		V1=V2=2*k;
		init();
		
		try {
			GRBEnv env = new GRBEnv("mip1.log");
			GRBModel model = new GRBModel(env);
			GRBVar constant1;
			constant1 = model.addVar(0, 1, 1, GRB.INTEGER, "constant");
			// variables z, x, y1->yi, y2->yj
			GRBVar[] z = new GRBVar[V1];
			GRBVar[][] x = new GRBVar[V1][V2];
			
			GRBVar[] y1 = new GRBVar[V1];
			for(int i=0; i<V1; i++) {
				z[i] = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "z_"+i);
				y1[i] = model.addVar(0, V1+1, 0, GRB.INTEGER, "y1_"+i);
				for(int j=0; j<V2; j++) {
					x[i][j] = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "x_"+i+""+j);
				}
			}
			
			model.update();
			
			// Objective: sum of z
			GRBLinExpr linExpr = new GRBLinExpr();
			GRBLinExpr right = new GRBLinExpr();
			for(int i=0; i<V1; i++)
				linExpr.addTerm(1.0, z[i]);
			model.setObjective(linExpr, GRB.MAXIMIZE);
			//edges constraints
			for (int i=0; i<V1; i++) {
				GRBLinExpr isum=new GRBLinExpr();
				for (int j=0; j<V1; j++) {
					isum.addTerm(1.0, x[i][j]);
				}
				model.addConstr(isum,GRB.EQUAL,2,i+"sum");
			}
			//Constraints
			for(int i=0; i<V1; i++) {
				linExpr = new GRBLinExpr();
				linExpr.addTerm(1, y1[i]);
				model.addConstr(linExpr, GRB.GREATER_EQUAL, 0, "c1_"+i);
				linExpr = new GRBLinExpr();
				linExpr.addTerm(1, y1[i]);
				model.addConstr(linExpr, GRB.LESS_EQUAL, i, "c2_"+i);
				linExpr = new GRBLinExpr();
				linExpr.addTerm(i, z[i]);
				model.addConstr(linExpr, GRB.LESS_EQUAL, y1[i], "c3_"+i);
				for(int j=0;j<V2;j++) {
					linExpr = new GRBLinExpr();
					GRBLinExpr linExpr1 = new GRBLinExpr();
					linExpr1.addTerm(-i, x[i][j]);
					linExpr1.addTerm(i, constant1);
					model.addConstr(linExpr, GRB.LESS_EQUAL, linExpr1, "c5_"+i);
					right.addTerm(1, y1[i]);
					right.addTerm(-1, y1[j]);
					model.addConstr(linExpr, GRB.GREATER_EQUAL, right, "c4_"+i);
					right=new GRBLinExpr();
					right.addTerm(-1, y1[i]);
					right.addTerm(1, y1[j]);
					linExpr1 = new GRBLinExpr();
					linExpr1.addTerm(-j, x[i][j]);
					linExpr1.addTerm(j, constant1);
					model.addConstr(linExpr, GRB.LESS_EQUAL, linExpr1, "c6_"+i);
				}
			}
			//TODO:constaints related to edges:fh=gh->ft=gt;
			for(int i=0;i<V1;i++) {
				for(int j=0;j<V2;j++) {
					model.addConstr(x[i][j], GRB.EQUAL, x[j][i], "edge_"+i+""+j);
				}
			}
			// Optimize
			
		    model.optimize();
		    System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));
		    
		    // dispose of model and env
		    model.dispose();
		    env.dispose();
				
		} catch (GRBException e) {
			System.out.println("Error code: " + e.getErrorCode() + ". " +
                      e.getMessage());
		}

	}

}

