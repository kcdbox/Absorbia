package de.itworks.absorbia.networking;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

/*
 * Absorbia_Server
 * 
 * @author Tobias Schmidt
 */
public class Absorbia_Server {

	private int TCP_PORT, UDP_PORT;
	private Server server;
	private Kryo kryo;
	public Object[] coordinateOfOpponenent;
	private Boolean isConnected;
	
	public Absorbia_Server(int tcp,  int udp) {
		Log.set(Log.LEVEL_INFO);
		this.TCP_PORT = tcp;
		this.UDP_PORT = udp;
		
		server = new Server();
		kryo = server.getKryo();
		setIsConnected(false);
	}
	
	public void startServer(){
		Log.info("Server startet");
		server.start();
		
		try {
			
			server.bind(TCP_PORT, UDP_PORT);
			server.addListener(new Listener(){
				public void received (Connection connection, Object object) {
					
					if (object instanceof Request){
						Request request = (Request) object;
						
						setCoordinateOfOpponenent(request.getCoordinate());
					}
				}
				
				public void connected (Connection connection) {
					setIsConnected(true);
		        }
				
				public void disconnected (Connection connection) {
					setIsConnected(false);
				}
			});
			
			registerKryo();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void registerKryo() {
		kryo.register(Request.class);
		kryo.register(Response.class);
		kryo.register(Object[].class);
	}

	public void stopServer(){
		Log.info("Server stoppt");
		server.stop();
	}

	public Object[] getCoordinateOfOpponenent() {
		try {
			server.update(500);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return coordinateOfOpponenent;
	}
	
	public void sendTheOwnCoordinates(Object[] object){
		Response res = new Response();
		res.setCoordinates(object);
		server.sendToAllTCP(res);
	}

	public void setCoordinateOfOpponenent(Object[] coordinateOfOpponenent) {
		this.coordinateOfOpponenent = coordinateOfOpponenent;
	}

	public Boolean getIsConnected() {
		try {
			server.update(500);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return isConnected;
	}

	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}
}
