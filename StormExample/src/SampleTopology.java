import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class SampleTopology {
	
	public static void main(String[] args) throws InterruptedException {
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("pull-data",new mqbrowse(),1);
		builder.setBolt("display-data",new XMLParse(),2).
			shuffleGrouping("pull-data");
		LocalCluster cluster = new LocalCluster();
		Config conf = new Config();
		
		cluster.submitTopology("SampleTopology1", conf, builder.createTopology());
	}	
	
}
