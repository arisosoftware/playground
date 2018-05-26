package com.arisosoftware.vertbench;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class BenchApp {
	 
		public static void main(String[] args) {

			Vertx vertx = Vertx.vertx();
			MainVerticle main = new MainVerticle();

			for (int no = 0; no < 10; no++) {
				WorkerVerticle worker = new WorkerVerticle();
				worker.WorkerId = no;
				//vertx.deployVerticle(worker);
				vertx.deployVerticle(worker, new DeploymentOptions().setWorker(true));
				//vertx.deployVerticle(worker, new DeploymentOptions().setWorker(true).setMultiThreaded(true));

			}

			vertx.deployVerticle(main);

		}
		


		final static String Topic = "Task"; 
		
}
