//package com.arisosoft.tensorflow;
//
//import org.tensorflow.Graph;
//import org.tensorflow.Session;
//import org.tensorflow.Tensor;
//import org.tensorflow.TensorFlow;
//import org.tensorflow.Tensors;
//
//public class Task01 {
//  @SuppressWarnings("rawtypes")
//public static void main(String[] args) throws Exception {
//	  
//	
////	    
//    try (Graph g = new Graph()) {
//      final String value = "Hello from TensorFlow " + TensorFlow.version();
//      
//      tf.logging.set_verbosity(tf.logging.INFO)
//      
//      final float[][] matrix = {{1, 2, 3}, {4, 5, 6}};
//      try (Tensor<Float> src = Tensors.create(matrix)) {
//        Tensor<Float> cpy = Tensor.fromHandle(src.getNativeHandle()).expect(Float.class);
////        assertEquals(src.dataType(), cpy.dataType());
////        assertEquals(src.numDimensions(), cpy.numDimensions());
////        assertArrayEquals(src.shape(), cpy.shape());
////        assertArrayEquals(matrix, cpy.copyTo(new float[2][3]));
//      }
//      
//      
//
//      // Construct the computation graph with a single operation, a constant
//      // named "MyConst" with a value "value".
//      try (Tensor t = Tensor.create(value.getBytes("UTF-8"))) {
//      
//    	  
//    	  // The Java API doesn't yet include convenience functions for adding operations.
//        g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build();
//        
//      
//        
//      }
//
//      // Execute the "MyConst" operation in a Session.
//      try (Session s = new Session(g);
//           Tensor output = s.runner().fetch("MyConst").run().get(0)) {
//        System.out.println(new String(output.bytesValue(), "UTF-8"));
//        
//
////
////		// Define a model for linear regression.
////	    const model = tf.sequential();
////	    model.add(tf.layers.dense({units: 1, inputShape: [1]}));
////	    // Prepare the model for training: Specify the loss and the optimizer.
////	    model.compile({loss: 'meanSquaredError', optimizer: 'sgd'});
////	    // Generate some synthetic data for training.
////	    const xs = tf.tensor2d([1, 2, 3, 4], [4, 1]);
////	    const ys = tf.tensor2d([1, 3, 5, 7], [4, 1]);
////	    // Train the model using the data.
////	    model.fit(xs, ys).then(() => {
////	        // Use the model to do inference on a data point the model hasn't seen before:
////	        result = model.predict(tf.tensor2d([5], [1, 1]));
////	    });
////	    
//        
//        
//      }
//    }
//  }
//}
//	 