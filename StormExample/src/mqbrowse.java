import com.ibm.mq.*;  // com.ibm.mq.jar
import com.ibm.mq.constants.MQConstants;


//import java.util.List;
import java.util.Map;



//Storm initialization
import backtype.storm.task.TopologyContext; // Topology Context
import backtype.storm.topology.OutputFieldsDeclarer;
//import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.spout.SpoutOutputCollector; //Spout I/O data collector
import backtype.storm.tuple.Fields; 
import backtype.storm.tuple.Values;
import backtype.storm.topology.IRichSpout;




public class mqbrowse implements IRichSpout {

//
private MQQueueManager MQMgr;
private SpoutOutputCollector collector;

public void nextTuple() {
	
	try 
	{
	  
	  /******************************************
	  * Connect to Queue Manager and Queue
	  ******************************************/	
	  MQMgr = new MQQueueManager("SampleQ");
	  int openOptions = MQConstants.MQOO_INPUT_EXCLUSIVE | MQConstants.MQOO_BROWSE;
	  MQQueue myQueue = MQMgr.accessQueue("QueQue1", openOptions, null, null, null);
	  
	  /******************************************
	   * Set up MQ to get message
	   ******************************************/
	  MQGetMessageOptions getMesgOpt = new MQGetMessageOptions();	  
	  MQMessage myMsg = new MQMessage();
	  getMesgOpt.waitInterval = MQConstants.MQWI_UNLIMITED;
	  
	  boolean done = false;
	  // Do-While Starts
	  do {
	  try {
	  //myMsg.clearMessage();
	  getMesgOpt.options = MQConstants.MQGMO_WAIT | MQConstants.MQGMO_BROWSE_NEXT;	  
	  myMsg.correlationId = MQConstants.MQCI_NONE;
	  myMsg.messageId = MQConstants.MQMI_NONE;
	  
	  
	  myQueue.get(myMsg, getMesgOpt);
	  String msg = myMsg.readLine();
	  collector.emit(new Values(msg));
	  
	  }
	  
	  
	  catch (MQException ex)
	  {
	  System.out.println("Error Read Message: " + ex.getMessage() + "RC: " + ex.reasonCode);
	  }
	  catch (Exception e) {
			System.out.print("Error: " + e.getMessage() );
			}
	 } while (!done);
	//Do-While Ends
	  
	  
	  myQueue.close();
	  MQMgr.disconnect();
	  
	}
	catch (MQException ex)
	{
	System.out.println("MQ Exception: " + ex.getMessage() + " RC =" + ex.reasonCode);
	}
	  catch (Exception e) {
			System.out.print("Error: " + e.getMessage() );
			}
}

public void open(Map Conf,TopologyContext context,SpoutOutputCollector collector) {
	  // Note: for flexible should pass in host name, channel, port number
	  System.out.println("Init...");
	  MQEnvironment.hostname = "localhost";
	  MQEnvironment.channel = "SYSTEM.ADMIN.SVRCONN";
	  MQEnvironment.port = 1414;
	  this.collector = collector;
}
public void declareOutputFields(OutputFieldsDeclarer declarer) {
	
	declarer.declare(new Fields("msg"));
}

@Override
public void ack(Object arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void activate() {
	// TODO Auto-generated method stub
	
}

@Override
public void close() {
	// TODO Auto-generated method stub
	
}

@Override
public void deactivate() {
	// TODO Auto-generated method stub
	
}

@Override
public void fail(Object arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public Map<String, Object> getComponentConfiguration() {
	// TODO Auto-generated method stub
	return null;
}

}

