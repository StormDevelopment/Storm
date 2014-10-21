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


public class TestXml {

	public static void main(String[] args) {
		//String temp = ''; 
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
}
