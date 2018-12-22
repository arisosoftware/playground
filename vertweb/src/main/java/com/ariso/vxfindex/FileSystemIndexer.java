// 
//package com.ariso.vxfindex;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.arisosoftware.vertbench.BenchApp;
//import com.arisosoftware.vertbench.MainVerticle;
//import com.arisosoftware.vertbench.WorkerVerticle;
//import com.github.rjeschke.txtmark.Processor;
//
//import io.vertx.core.AbstractVerticle;
//import io.vertx.core.DeploymentOptions;
//import io.vertx.core.Future;
//import io.vertx.core.Vertx;
//import io.vertx.core.http.HttpServer;
//import io.vertx.core.json.JsonArray;
//import io.vertx.core.json.JsonObject;
//import io.vertx.ext.jdbc.JDBCClient;
//import io.vertx.ext.sql.SQLConnection;
//import io.vertx.ext.web.Router;
//import io.vertx.ext.web.RoutingContext;
//import io.vertx.ext.web.handler.BodyHandler;
//import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;
//
// 
//public class FileManagerApp {
//
//    public void Start()
//    {
//        Vertx vertx = Vertx.vertx();
//
//        MainVerticle main = new MainVerticle();
//        main.TotalTasks = this.TotalTasks;
//
//        for (int no = 0; no < this.TotalWorkers; no++) {
//            WorkerVerticle worker = new WorkerVerticle();
//            worker.WorkerId = no;
//            worker.HashResultMask = this.HashResultMask;
//            worker.HashResultPattern = this.HashResultPattern;
//            vertx.deployVerticle(worker, new DeploymentOptions().setWorker(true).setMultiThreaded(true)
//                    .setWorkerPoolSize(this.WorkerPoolSize));
//
//        };
//
//        vertx.deployVerticle(main);
//    }
//    
//    public static void main(String[] args) {
//        FileManagerApp app = new BenchApp();
//        app.Start();
//    }
//}
