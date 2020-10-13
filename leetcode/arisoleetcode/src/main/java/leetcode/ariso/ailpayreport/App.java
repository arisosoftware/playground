package leetcode.ariso.ailpayreport;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    { 
    	AnalysisAlipayCSV csv = new AnalysisAlipayCSV();
    	csv.Parse("/tmp/alipay.csv");
    }
}
