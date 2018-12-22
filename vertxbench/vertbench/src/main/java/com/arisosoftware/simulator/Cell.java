package com.arisosoftware.simulator;

import java.util.Random;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;

public class Cell extends AbstractVerticle {

	// public int x;
//	public int y;

	public int life;
	public int power;

	Random rnd;

	@Override
	public void start() throws Exception {

		EventBus eb = vertx.eventBus();
		rnd = new Random();
		life = 10 + rnd.nextInt(10);
		power = 0;

		String ID = this.deploymentID();
	//	System.out.println(String.format("new cell life %d power %d  %s", life, power, ID));
		eb.send(MessageKey.MSG_NewVx, ID);

		eb.consumer(MessageKey.MSG_Step).handler(messge -> {
			if (life > 0) {
				life--;
				power++;
			//	System.out.println(String.format("step cell life %d power %d ID  ", life, power, ID));
				if (life < 0) {
				//	System.out.println(String.format("del cell life %d power %d ", life, power));
					eb.send(MessageKey.MSG_DelVx, this.deploymentID());
				//	vertx.undeploy(this.deploymentID());
					try {
						this.stop();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (power > 9) {
					power = 0;
					Cell anotherC = new Cell();
					vertx.deployVerticle(anotherC);
				}
			}
		});
 
	}

}
