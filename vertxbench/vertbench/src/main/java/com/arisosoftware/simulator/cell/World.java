package com.arisosoftware.simulator.cell;

import java.util.ArrayList;

import com.arisosoftware.vertbench.BenchApp;
import com.arisosoftware.vertbench.MainVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;

public class World extends AbstractVerticle {

	long totalCell = 0;
    long totalFood =0;
   
    

	public static void main(String[] args) {
	
		Vertx vertx = Vertx.vertx();

		World world = new World();
	    Cell firstOne = new Cell();
		vertx.deployVerticle(world, new DeploymentOptions().setWorker(true).setMultiThreaded(true));
		vertx.deployVerticle(firstOne, new DeploymentOptions().setWorker(true).setMultiThreaded(true));
		 

	}

	
	@Override
	public void start() throws Exception {
		System.out.println("[SystemVx] Running in " + Thread.currentThread().getName());

		EventBus eb = vertx.eventBus();
  

		vertx.setPeriodic(1, id -> {
			eb.publish(MessageKey.MSG_Step, "B");
		});
		
		int TestCount =100000;
	    long[] runtimelog = new long[TestCount];

		MessageConsumer<String> consumer = eb.consumer(MessageKey.MSG_NewVx);
		consumer.handler(message -> {
			this.totalCell ++;
		});

		eb.consumer(MessageKey.MSG_DelVx).handler(msg ->{
			this.totalCell --;
		});
		 
		for(int i=0;i<100000;i++)
		{
			Thread.sleep(500);
			eb.publish(MessageKey.MSG_Step, "");
			runtimelog[i] = this.totalCell;
			System.out.println(String.format("# %d \t %d", i, this.totalCell));
		}
		
//
//		 // Called when verticle is deployed
//		 public void start() {
//		 }
//
//		 // Optional - called when verticle is undeployed
//		 public void stop() {
//		 }
//		 

	}

}
