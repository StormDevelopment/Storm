import com.ibm.mq.*;  // com.ibm.mq.jar
import com.ibm.mq.constants.MQConstants;

import java.util.Map;

public class ConnectQ {

private MQQueueManager MQMgr;

public MQMessage init() throws MQException
{
// Note: for flexible should pass in host name, channel, port number;
MQEnvironment.hostname = "nmesbdev21";
MQEnvironment.channel = "NM.SVR.CONN";
MQEnvironment.port = 1421;	 
MQMgr = new MQQueueManager("SampleQ");
int openOptions = MQConstants.MQOO_INPUT_EXCLUSIVE | MQConstants.MQOO_BROWSE;
MQQueue myQueue = MQMgr.accessQueue("QueQue1", openOptions, null, null, null);
MQMessage myMsg = new MQMessage();
MQGetMessageOptions getMesgOpt = new MQGetMessageOptions();
getMesgOpt.waitInterval = MQConstants.MQWI_UNLIMITED;
return myMsg;
}

	 	  
	 
	  
	  
}

