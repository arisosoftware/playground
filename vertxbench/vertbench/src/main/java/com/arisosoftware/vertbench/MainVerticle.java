package com.arisosoftware.vertbench;

import io.vertx.core.AbstractVerticle;

/**
 * An example illustrating how worker verticles can be deployed and how to
 * interact with them.
 *
 * This example prints the name of the current thread at various locations to
 * exhibit the event loop <-> worker thread switches.
 */
public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		System.out.println("[Main] Running in " + Thread.currentThread().getName());
		for (int questionId = 0; questionId < 5; questionId++) {
			vertx.eventBus().send(BenchApp.Topic, "hello world #" + questionId, r -> {

				if (r.succeeded()) {
					System.out.println("[Main] Receiving reply '" + r.result().body() + "' in "
							+ Thread.currentThread().getName());
				}
			});

		}

	}
}
