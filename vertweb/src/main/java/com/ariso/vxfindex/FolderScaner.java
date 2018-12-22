//package com.ariso.vxfindex;
//
// 
//import io.vertx.core.AbstractVerticle;
//import io.vertx.core.Handler;
//import io.vertx.core.eventbus.EventBus;
//import io.vertx.core.eventbus.Message;
//
//public class FolderScaner extends AbstractVerticle {
// 
//    @Override
//    public void start() throws Exception {
//       
//        EventBus eb = vertx.eventBus();
//
//        for (int questionId = 0; questionId < TotalTasks; questionId++) {
//
//            String message = "hello world #" ;//+ questionId;
//
//            eb.send(BenchApp.Topic, message);
//
//        }
//   
//        ReduceHandler reduce = new ReduceHandler();
//        reduce.total = TotalTasks;
//        
//        eb.consumer(BenchApp.TopicResult, reduce);
//
//    }
//
//    class ReduceHandler implements Handler<Message<Object>> {
//        int total;
//        @Override
//        public void handle(Message<Object> message) {
//            total--;
//            System.out.println("Receive: "+total+" "+ message.body());
//
//            if (total == 0) {
//                vertx.eventBus().publish(BenchApp.TopicShutdown, "shutdown");
//
//                // then print all timeinfo
//                sw.Stop();
//                System.out.println("=================\n\nTotal:"+sw.Report());
//                System.exit(0);
//            }
//             
//        }
//
//    }
//
//}
