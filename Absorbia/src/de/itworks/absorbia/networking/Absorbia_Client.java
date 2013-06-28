package de.itworks.absorbia.networking;

import java.io.IOException;
import java.net.InetAddress;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

/*
 * Absorbia_Client
 * 
 * @author Tobias Schmidt
 */
public class Absorbia_Client {
	
	private int TCP_PORT, UDP_PORT, TIMEOUT;
	private Client client;
	private Kryo kryo;
	public Object[] coordinateOfOpponenent;
	
	public Absorbia_Client(int tcp, int udp, int timeout) {
		Log.set(Log.LEVEL_INFO);
		
		this.TCP_PORT = tcp;
		this.UDP_PORT = udp;
		this.TIMEOUT = timeout;
	
		client = new Client();
		kryo = client.getKryo();
	}
	
	public void connect(InetAddress inetAddress){
		Log.info("Client gestartet");
		
		try {
			client.start();
			client.connect(TIMEOUT, inetAddress, TCP_PORT, UDP_PORT);
			client.addListener(new Listener(){
				public void received (Connection connection, Object object) {

					Log.set(Log.LEVEL_INFO);
					
					if (object instanceof Response){
						Response response = (Response) object;
		
						setCoordinateOfOpponenent(response.getCoordinates());
					}
					
				}
			});
			
			registerKryo();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendTheOwnCoordinates(Object[] object){
		Request req = new Request();
		req.setCoordinate(object);
		client.sendTCP(req);
	}
	
	public InetAddress seekServer(){
		return client.discoverHost(this.UDP_PORT, 1000);
	}
	
	public void disconnect(){
		Log.info("Client gestoppt");
		client.stop();
	}
	
	private void registerKryo() {
		kryo.register(Request.class);
		kryo.register(Response.class);
		kryo.register(Object[].class);
	}
	
	public Object[] getCoordinateOfOpponenent() {
		try {
			client.update(500);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return coordinateOfOpponenent;
	}
	
	public void setCoordinateOfOpponenent(Object[] coordinateOfOpponenent) {
		this.coordinateOfOpponenent = coordinateOfOpponenent;
	}
}
