
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private Panel panel;

	private int x=30, y=20;
	int vel = 10;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 545, 625);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#320139"));
		//		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new Panel();
		panel.setBackground(Color.decode("#333e50"));
		contentPane.add(panel, BorderLayout.CENTER);

		panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

//				System.out.println(e.getKeyCode());

				switch(e.getKeyCode()) {
				case 37:
					if(x>10)
						x-=vel;

					break;
				case 38:
					if(y>10)
						y-=vel;

					break;
				case 39:
					if(x<630)
						x+=vel;

					break;
				case 40:
					if(y<520)
						y+=vel;

					break;

				}

				System.out.println("Player X: "+x+"\n"
						+"Player Y: "+y+"\n");
				
				panel.repaint();

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		panel.setFocusable(true);
		panel.requestFocus();

		JPanel panelReiniciar = new JPanel();
		panelReiniciar.setBackground(Color.decode("#320139"));
		contentPane.add(panelReiniciar, BorderLayout.SOUTH);

		JButton reiniciar = new JButton("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setFocusable(true);
				panel.requestFocus();
			}

		});
		panelReiniciar.add(reiniciar);

	}

	private class Panel extends JPanel{

		public Rect formerPlayer;
		public Rect player;
		public Rect[] wall;

		boolean collision = false;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//fondo
			g.setColor(Color.red);
			g.fillRect(10, 10, this.getWidth()-20, this.getHeight()-20);

			//player
			player = new Rect(x, y, 10, 10, Color.BLUE);
			g.setColor(player.c);
			g.fillRect(player.x, player.y, player.w, player.h);

			int aux = 0;
			
			//create all the walls
			wall = new Rect[100];

			//left wall
			wall[aux++] = new Rect(20, 20, 10, 500, Color.white);

			//down wall
			wall[aux++] = new Rect(20, 520, 490, 10, Color.white);

			//top wall
			wall[aux++] = new Rect(60, 20, 450, 10, Color.white);

			//right wall
			wall[aux++] = new Rect(500, 20, 10, 470, Color.white);

			//vertical walls
			wall[aux++] = new Rect(60, 30, 10, 30, Color.white);
			wall[aux++] = new Rect(100, 60, 10, 160, Color.white);
			wall[aux++] = new Rect(140, 30, 10, 30, Color.white);
			wall[aux++] = new Rect(180, 60, 10, 120, Color.white);
			wall[aux++] = new Rect(220, 50, 10, 50, Color.white);
			wall[aux++] = new Rect(340, 50, 10, 50, Color.white);
			wall[aux++] = new Rect(380, 70, 10, 70, Color.white);
			wall[aux++] = new Rect(420, 30, 10, 110, Color.white);
			wall[aux++] = new Rect(460, 70, 10, 70, Color.white);
			wall[aux++] = new Rect(140, 180, 10, 40, Color.white);
			wall[aux++] = new Rect(220, 130, 10, 90, Color.white);
			wall[aux++] = new Rect(160, 210, 10, 40, Color.white);
			wall[aux++] = new Rect(260, 90, 10, 40, Color.white);
			wall[aux++] = new Rect(300, 90, 10, 50, Color.white);

			
			//horizontal walls
			wall[aux++] = new Rect(20, 90, 130, 10, Color.white);
			wall[aux++] = new Rect(110, 170, 40, 10, Color.white);
			wall[aux++] = new Rect(140, 50, 50, 10, Color.white);
			wall[aux++] = new Rect(150, 130, 30, 10, Color.white);
			wall[aux++] = new Rect(190, 90, 30, 10, Color.white);
			wall[aux++] = new Rect(230, 50, 110, 10, Color.white);
			wall[aux++] = new Rect(150, 210, 70, 10, Color.white);
			wall[aux++] = new Rect(230, 130, 40, 10, Color.white);
			wall[aux++] = new Rect(260, 90, 50, 10, Color.white);
			wall[aux++] = new Rect(300, 140, 50, 10, Color.white);

			
			//crea el numero exacto de muros, para no tener que aumentarlo a mano
			int aux2 = aux;
			
			for (int i = 0; i < 100-aux2 ; i++) {
				wall[aux++] = new Rect(0, 0, 10, 10, Color.white);

			}
			
			
			g.setColor(Color.white);

			for (int i = 0; i < wall.length; i++) {

				g.fillRect(wall[i].x, wall[i].y, wall[i].w, wall[i].h);	
			}

			for (int i = 0; i < wall.length; i++) {

				if(player.collision(wall[i]))
					collision = true;

			}
			if(!collision) {
				formerPlayer = new Rect(player);
			}else {

				x = formerPlayer.x;
				y = formerPlayer.y;

				g.setColor(player.c);
				g.fillRect(formerPlayer.x, formerPlayer.y, formerPlayer.w, formerPlayer.h);
				collision = false;
			}


			
			//		        System.out.println(player.collision(wall));

		}

	};


	public class Rect {

		int x;
		int y;
		int w;
		int h;
		Color c;

		Rect(int x, int y, int w, int h, Color c){

			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.c = c;

		}

		Rect(Rect rect){

			this.x = rect.x;
			this.y = rect.y;
			this.w = rect.w;
			this.h = rect.h;
			this.c = rect.c;

		}

		public boolean collision(Rect target) {
			if(x < target.x +target.w && 
					x+w > target.x &&
					y < target.y +target.h &&
					y+h > target.y) {
				return true;
			}

			return false;
		}

	}

}
