package com.arisosoftware.vertbench;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BenchApp {

	public int TotalTasks;
	public int TotalWorkers;
	public int WorkerPoolSize;

	public int HashResultMask;
	public int HashResultPattern;

	public static void LoadConfig() {

		// example of how system properties override; note this
		// must be set before the config lib is used
		System.setProperty("simple-lib.whatever", "This value comes from a system property");

		// Load our own config values from the default location,
		// application.conf
		Config conf = ConfigFactory.load();

		// Config defaultConfig = ConfigFactory.parseResources("defaults.conf");
		System.out.println("The answer is: " + conf.getString("simple-app.answer"));

		// In this simple app, we're allowing SimpleLibContext() to

	}

	public BenchApp() {
		Config config = ConfigFactory.parseResources("app.config");
		//
		TotalTasks = config.getInt("conf.TotalTasks");
		TotalWorkers = config.getInt("conf.TotalWorkers");
		WorkerPoolSize = config.getInt("conf.WorkerPoolSize");

		HashResultMask = Integer.parseUnsignedInt(config.getString("conf.HashResultMask").replace("_", ""), 16);// 0xff_fff_000;
		HashResultPattern = Integer.parseUnsignedInt(config.getString("conf.HashResultPattern").replace("_", ""), 16);// 0x12_300_000

	
	}

	public void Start()
	{
		Vertx vertx = Vertx.vertx();

		MainVerticle main = new MainVerticle();
		main.TotalTasks = this.TotalTasks;

		for (int no = 0; no < this.TotalWorkers; no++) {
			WorkerVerticle worker = new WorkerVerticle();
			worker.WorkerId = no;
			worker.HashResultMask = this.HashResultMask;
			worker.HashResultPattern = this.HashResultPattern;
			vertx.deployVerticle(worker, new DeploymentOptions().setWorker(true).setMultiThreaded(true)
					.setWorkerPoolSize(this.WorkerPoolSize));

		};

		vertx.deployVerticle(main);
	}
	
	public static void main(String[] args) {
		BenchApp app = new BenchApp();
		app.Start();
	}

	final static String ConfigName = "VertBench.json";
	final static String Topic = "Topic";
	final static String TopicResult = "TopicResult";
	final static String TopicShutdown = "TopicShutdown";
}
