
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

	private int x=90, y=90;
	
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
		setBounds(600, 200, 665, 615);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#320139"));
//		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
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
				
				System.out.println(e.getKeyCode()); 
				
				int vel = 10;

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
	
	private JPanel panel = new JPanel() {
		
		 @Override
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        //fondo
		        g.setColor(Color.red);
		        g.fillRect(10, 10, this.getWidth()-20, this.getHeight()-20);

		        //player
		        Rect player = new Rect(x, y, 10, 10, Color.BLUE);
		        g.setColor(player.c);
		        g.fillRect(player.x, player.y, player.w, player.h);

		        //wall
		        Rect wall = new Rect(90, 180, 30, 300, Color.gray);
		        g.setColor(wall.c);
		        g.fillRect(wall.x, wall.y, wall.w, wall.h);	

		        System.out.println(player.colision(wall));

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
		
		public boolean colision(Rect target) {
			if(x < target.x +target.w && 
					x+w > target.x &&
					y < target.y +target.h &&
					y+h >target.y) {
				return true;
			}
			
			return false;
		}

	}
	
}
