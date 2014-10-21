
import java.sql.*;
import java.util.Map;
import java.io.*;
import java.util.Properties;

import backtype.storm.task.TopologyContext;
import backtype.storm.task.OutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import javax.xml.bind.*;

import generated.*;


public class XMLParse extends BaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void execute(Tuple msg1) {
		String temp = msg1.getString(0);
		StringReader reader = new StringReader(temp);
		try {
			JAXBContext jc = JAXBContext.newInstance(ROWSET.class);
			Unmarshaller jum = jc.createUnmarshaller();
			ROWSET rowset = (ROWSET) jum.unmarshal(reader);
			System.out.println(rowset.getROW());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public void prepare(Map Conf,TopologyContext context,OutputCollector collector) {
	}
	

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
