package game;
import java.awt.*;
import javax.swing.JFrame;

import tiles.Tile;

import java.applet.*;

public class DrawCanvas extends GameView
{

	private static final int WORLD_SIZE_X = 0;
	private static final int VIEWPORT_SIZE_X = 0;
	private static final int WORLD_SIZE_Y = 0;
	private static final int VIEWPORT_SIZE_Y = 0;
	private int camX;
	private int camY;
	private int playerX;
	private int playerY;
	private int offsetMinX;
	private int offsetMaxY;
	private int offsetMaxX;
	private int offsetMinY;
	
	

	private GameView canvas = new GameView();
	public DrawCanvas()
	{
		
		offsetMaxX = WORLD_SIZE_X - VIEWPORT_SIZE_X;
		offsetMaxY = WORLD_SIZE_Y - VIEWPORT_SIZE_X;
		offsetMinX = 0;
		offsetMinY = 0;
		
		//calculate the position of camera, this centers the rowdy's position
		camX = playerX - VIEWPORT_SIZE_X / 2;
		camY = playerY - VIEWPORT_SIZE_Y / 2;
		
		//checks camera position to make sure it does not exceed offsets.
		
		if (camX > offsetMaxX)
		{
			camX = offsetMaxX;
		}
		
		else if (camX < offsetMinX)
		{
			camX = offsetMinX;
		}
		
		if(camY > offsetMaxY)
		{
			camY = offsetMaxY;
		}
		
		else if(camY < offsetMinY)
		{
			camY = offsetMinY;
		}
	
	}
	//translate world to position
	public void render(Graphics g)
	{
		g.translate(-camX, -camY);
	}
}

