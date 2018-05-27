package com.arisosoftware.vertbench;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class BenchApp {

	public static void main(String[] args) {

		Vertx vertx = Vertx.vertx();

		ConfigStoreOptions fileStore = new ConfigStoreOptions().setType("file").setOptional(true);

		ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(fileStore);

		ConfigRetriever retriever = ConfigRetriever.create(vertx, options);

		retriever.getConfig(ar -> {
			JsonObject config = null;
			if (ar.failed()) {

				config = new JsonObject();
				config.put("TotalTasks", "50");
				config.put("TotalWorkers", "50");
				config.put("WorkerPoolSize", "5");
				config.put("HashResultMask", "ff_fff_000");
				config.put("HashResultPattern", "12_300_000");

				System.out.println("default config update, please restart");
				System.exit(9);

			} else {
				config = ar.result();

				int TotalTasks = config.getInteger("TotalTasks");
				int TotalWorkers = config.getInteger("TotalWorkers");
				int WorkerPoolSize = config.getInteger("WorkerPoolSize");

				int HashResultMask = Integer.parseInt(config.getString("HashResultMask"), 16);// 0xff_fff_000;
				int HashResultPattern = Integer.parseInt(config.getString("HashResultPattern"), 16);// 0x12_300_000

				MainVerticle main = new MainVerticle();
				main.TotalTasks = TotalTasks;

				for (int no = 0; no < TotalWorkers; no++) {
					WorkerVerticle worker = new WorkerVerticle();
					worker.WorkerId = no;
					worker.HashResultMask = HashResultMask;
					worker.HashResultPattern = HashResultPattern;
					vertx.deployVerticle(worker, new DeploymentOptions().setWorker(true).setMultiThreaded(true)
							.setWorkerPoolSize(WorkerPoolSize));

					// vertx.deployVerticle(worker);
					// vertx.deployVerticle(worker, new DeploymentOptions().setWorker(true));

				}

				vertx.deployVerticle(main);

			}
		});

	}

	final static String Topic = "Topic";
	final static String TopicResult = "TopicResult";
	final static String TopicShutdown = "TopicShutdown";
}
