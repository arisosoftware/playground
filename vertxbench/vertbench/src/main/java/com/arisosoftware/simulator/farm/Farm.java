package com.arisosoftware.simulator.farm;

import java.util.ArrayList;
import java.util.LinkedList;

import com.arisosoftware.MyLinkedList;
import com.arisosoftware.LinkedNode;
import com.arisosoftware.vertbench.BenchApp;
import com.arisosoftware.vertbench.MainVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;

public class Farm extends AbstractVerticle {

	long totalRabbit = 0;
	long totalFood = 10000; // the init food = 10000 per acre
	long tick = 0;
	long halfFoodSetting = totalFood - (totalFood / 2);
	long lowFoodSetting = totalFood / 200;
	long maxiumFoodSetting = totalFood * 5;

	int StrongRabbitCount = 0;

	float FoodRate = 0.0f;
	float smallRate;
	float largeRate;
	float oldRate;
	

	
	int smallCount;
	int largeCount;
	int oldCount;
	

	float FatRate;
	float NofoodRate;

	static final int RabbitMaxAge = 20;
	static final int smallRabbitAgeDefine = 5;
	static final int largeRabbitAgeDefine = 10;
	static final int oldRabbitAgeDefine = 15;
	static final int fatRabbitDefine = 10;

	MyLinkedList<Rabbit> RabbitArray = new MyLinkedList<Rabbit>();

	/*
	 * 这是主要循环，规则如下 对兔子队列中的每一只兔子， 食物充沛 兔子吃饱，脂肪加1，消耗2份食物 食物不够 兔子吃半饱，脂肪不变，消耗1份食物 食物缺乏
	 * 兔子没得吃，脂肪减去1，消耗0食物
	 * 
	 * 兔子健康处理 当兔子脂肪大于10，那么兔子可以繁殖小兔，1只变5只，脂肪减少为1 小兔初始脂肪为1。
	 * 兔子最多可以存在30步。也就是，食物充沛时候，可以繁殖3代。 超过30步的兔子会从兔子列表中删除。 脂肪为-3的兔子也会从兔子列表中删除
	 * 
	 * 食物循环，每隔10步，食物的数量会是当时食物数量的1倍。但不能超过上限。
	 * 
	 */
	public static void handleNextStep(Farm farm) {
		ArrayList<Rabbit> newGenRabbit = new ArrayList<Rabbit>();
		ArrayList<LinkedNode> tobedelete = new ArrayList<LinkedNode>();
		LinkedNode<Rabbit> linkedNode = farm.RabbitArray.getFirstNode();
		int count = 0;

		int aRabbit = 0;
		int aaRabbit = 0;
		int aaaRabbit = 0;

		if (farm.totalRabbit == 0) {
			farm.totalRabbit = farm.RabbitArray.size();
		}

		if (farm.RabbitArray.size() == 0) {
			System.exit(0);
		}

		farm.FoodRate = farm.totalFood / farm.totalRabbit;

		while (linkedNode != null) {
			Rabbit rabbit = (Rabbit) linkedNode.getData();
			rabbit.life = rabbit.life + 1;

			if (rabbit.life > 10 && rabbit.life < 20 && rabbit.fat > 2) {
				count++;
			}

			if (rabbit.life < smallRabbitAgeDefine)
				aRabbit++;
			else if (rabbit.life < largeRabbitAgeDefine)
				aaRabbit++;
			else
				aaaRabbit++;

//old -rule
//			if (farm.totalFood > farm.halfFoodSetting) {
//				rabbit.fat = rabbit.fat + 1;
//				farm.totalFood = farm.totalFood - 2;
//			} else if (farm.totalFood > farm.lowFoodSetting) {
//				farm.totalFood = farm.totalFood - 1;
//			} else {
//				rabbit.fat = rabbit.fat - 1;
			// }

			if (farm.FoodRate > 20.0f) {
				rabbit.fat = rabbit.fat + 1;
				farm.totalFood = farm.totalFood - 2;
			} else if (farm.FoodRate > 10.0f) {
				if (count < 10) {
					rabbit.fat++;
					farm.totalFood = farm.totalFood - 2;
				}
				farm.totalFood = farm.totalFood - 1;
			} else if (farm.FoodRate > 5f) {
				if (count < 10) {
					rabbit.fat++;
					farm.totalFood = farm.totalFood - 2;
				} else if (count > farm.totalFood) {
					rabbit.fat--;
				}
			} else if (farm.FoodRate < 1f) {
				if (count < 10) {
					rabbit.fat++;
					farm.totalFood = farm.totalFood - 2;
				} else if (count > farm.totalFood) {
					rabbit.fat--;
				} else {
					farm.totalFood = farm.totalFood - 1;
				}
			}

			if (rabbit.life > RabbitMaxAge) {
				tobedelete.add(linkedNode);
			} else if (rabbit.fat > fatRabbitDefine) {
				int nextGenCount = 5;
				if (count < 10 && farm.FoodRate < 10) {
					nextGenCount = 2;
				}

				for (int j = 0; j < nextGenCount; j++) {
					Rabbit newRabbit = new Rabbit();
					newRabbit.fat = 1;
					newGenRabbit.add(newRabbit);
				}
				rabbit.fat = 1;
			} else if (rabbit.fat < -1 && rabbit.life < smallRabbitAgeDefine) {
				tobedelete.add(linkedNode);
			} else if (rabbit.fat < -2) {
				tobedelete.add(linkedNode);
			}

			linkedNode = linkedNode.next;
		}

		farm.RabbitArray.addAll(newGenRabbit);
		farm.RabbitArray.deleteAll(tobedelete);
		farm.eb.send(MESSAGE.NewUnit, "" + newGenRabbit.size());
		farm.eb.send(MESSAGE.RemoveUnit, "" + tobedelete.size());

		farm.StrongRabbitCount = count;
		farm.tick++;

		if (farm.tick % (RabbitMaxAge + 1) == 0) {
			farm.totalFood = farm.totalFood * 2;
			if (farm.totalFood > farm.maxiumFoodSetting) {
				farm.totalFood = farm.maxiumFoodSetting;
			}
		}
 
		farm.smallCount = aRabbit;
		farm.largeCount = aaRabbit ;
		farm.oldCount = aaaRabbit ;
		
		farm.smallRate = aRabbit *1.0f / farm.totalRabbit ;
		farm.largeRate = aaRabbit  *1.0f/ farm.totalRabbit ;
		farm.oldRate = aaaRabbit  *1.0f/ farm.totalRabbit ;

	}

	public static void ReportStatus(Farm farm) {
		System.out.println(String.format("%03d %6d %8d %g %5d %-5d %-5d %-5d",
				farm.tick, farm.totalRabbit, farm.totalFood,
				farm.FoodRate, farm.StrongRabbitCount,
				farm.smallCount, farm.largeCount, farm.oldCount

		));
	}

	public EventBus eb;

	public static void main(String[] args) {

		Vertx vertx = Vertx.vertx();

		Farm farm = new Farm();

		Rabbit firstOne = new Rabbit();
		farm.RabbitArray.add(firstOne);
		vertx.deployVerticle(farm);
		farm.eb = vertx.eventBus();
		farm.eb.send(MESSAGE.NewUnit, "1");
		// vertx.deployVerticle(world, new
		// DeploymentOptions().setWorker(true).setMultiThreaded(true));

		vertx.setPeriodic(100, id -> {

			farm.eb.send(MESSAGE.NextStep, "");
		});

		vertx.setPeriodic(500, id -> {

			farm.eb.send(MESSAGE.ReportStatus, "");
		});
	}

	@Override
	public void start() throws Exception {
		System.out.println("[SystemVx] Running in " + Thread.currentThread().getName());
		System.out.println("Tick,Rabbit,Food,Rate,StrongRabbit");

		EventBus eb = vertx.eventBus();

		eb.consumer(MESSAGE.NewUnit).handler(msg -> {
			int newrabbit = Integer.parseInt(msg.body().toString());
			this.totalRabbit = this.totalRabbit + newrabbit;
		});

		eb.consumer(MESSAGE.RemoveUnit).handler(msg -> {
			int delrabbit = Integer.parseInt(msg.body().toString());
			this.totalRabbit = this.totalRabbit - delrabbit;
		});

		eb.consumer(MESSAGE.NextStep).handler(msg -> {
			handleNextStep(this);
		});
		eb.consumer(MESSAGE.ReportStatus).handler(msg -> {
			ReportStatus(this);
		});
	}

}
