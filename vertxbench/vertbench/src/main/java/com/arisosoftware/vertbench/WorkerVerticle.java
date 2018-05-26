package com.arisosoftware.vertbench;

import io.vertx.core.AbstractVerticle;

/**
 * An example of worker verticle
 */
public class WorkerVerticle extends AbstractVerticle {

	public int WorkerId = 0;

	@Override
	public void start() throws Exception {

		vertx.eventBus().<String>consumer(BenchApp.Topic, message -> {

			StopWatchInfo info = new StopWatchInfo();
			info.Message = "Worker name " + Thread.currentThread().getName() + "." + Thread.currentThread().getId();

			String body = message.body();
			int mashcode = 0xffffff00;

			String reply = String.format("Sorry, no found! %s ", body);
			for (int i = 0; i < 100000000; i++) {
				String Bx = body + i;

				int hash32 = Murmur3.hash32(Bx.getBytes());
				if ((hash32 & mashcode) == mashcode) {

					reply = String.format("Found! [%s] + [%d] = [%X]", body, i, hash32);
					break;
				}

			}
			info.Stop();

			reply = String.format("Worker:%d trace:%s\nsay:%s", this.WorkerId, reply, info.Report());
			message.reply(reply);

		});
	}
}
