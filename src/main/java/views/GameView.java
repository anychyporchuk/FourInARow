package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;


import javax.swing.SwingConstants;

import controllers.GameController;
import controllers.ResultController;
import controllers.ResultControllerImpl;
import models.DataContainer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class GameView {
	private ResultController resultController;
	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView window = new GameView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameView() {
		GameController.initializeGame();
		resultController = new ResultControllerImpl();
		initialize();
	}
	private final static int SIZE = 100;
	private Color[] playerColors;
	JLabel lblPlayersInfo;
	JPanel mainPanel;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		lblPlayersInfo = new JLabel("player's info");
		lblPlayersInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayersInfo.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblPlayersInfo, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameController.initializeGame();
				initializePlayers();
				clearBoard();
			}
		});
		btnNewButton.setBackground(new Color(240, 240, 240));
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		mainPanel = new JPanel();
		mainPanel.setSize(700, 700);
		initializePlayers();
		initializeBoard();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 *Calls after every turn.
	 */
	private void changeTurn(int row, int col)
    {

        if (resultController.checkResult(row, col))
        {
        	lblPlayersInfo.setText("Player " + (GameController.currentPlayer + 1) + " wins!");
            GameController.canPlay = false;
        }
        else
        { 
            GameController.currentPlayer = (GameController.currentPlayer == 1) ? 0 : 1;
            lblPlayersInfo.setText("Player's " + (GameController.currentPlayer + 1) + " turn");
        }
    }
	
	private void initializePlayers()
    {
        playerColors = new Color[2];
        playerColors[0] = Color.red;
        playerColors[1] = Color.blue;
        GameController.currentPlayer = 0;
        lblPlayersInfo.setText("Player's " + (GameController.currentPlayer + 1) + " turn");
    }
	
	/**
	 * Creates gameboard. Sets events on elements.
	 */
	private void initializeBoard()
    {
        for (int i = 0; i < DataContainer.ROWSC + 1; i++)
            for (int j = 0; j < DataContainer.COLUMNSC; j++)
            {
                
            	final JPanel currPB = new JPanel();
                currPB.setLocation(j * SIZE, (i + 1) * SIZE);

                currPB.setPreferredSize( new Dimension(SIZE, SIZE) );
                if (i != 0)
                {
                    currPB.setName("cv_" + i + j);
                    currPB.setBorder(BorderFactory.createLineBorder(Color.black));
                }
                else
                {
                    currPB.setName("cv_" + j);
                    currPB.addMouseListener(new MouseListener() {
						
						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mouseExited(MouseEvent e) {
							if(GameController.canPlay){
								currPB.setBackground(mainPanel.getBackground());
							}
							
						}
						
						@Override
						public void mouseEntered(MouseEvent e) {
							if(GameController.canPlay){
								currPB.setBackground(playerColors[GameController.currentPlayer]);
							}
							
							
						}
						
						@Override
						public void mouseClicked(MouseEvent e) {
							if(GameController.canPlay){
								int col = Integer.parseInt(currPB.getName().split("_")[1]);
								int pos = GameController.getPosition(col);
								
								
								if(pos == DataContainer.FULL){
									paintCoin(1, col);
									lblPlayersInfo.setText("Friendship wins!");
									
								}
								else if (pos != DataContainer.ROWSC){
									int row = DataContainer.ROWSC - pos;
									currPB.setBackground(playerColors[GameController.currentPlayer^1]);
									paintCoin(row, col);
									changeTurn(DataContainer.ROWSC - row,col);
				                }
							}
							
						}
					});

                }
                mainPanel.add(currPB);
            } 
    }
	
	/**
	 * Paints board coin in current player color 
	 * @param row - desired row
	 * @param col - desired column
	 */
	private void paintCoin(int row, int col){
		String str = "cv_"+row+col;
		
		for(Component c : mainPanel.getComponents()){
			if(c.getName().equals(str)){
				c.setBackground(playerColors[GameController.currentPlayer]);
				break;
			}
		}
	}
	/**
	 * Set all coins empty.
	 */
	private void clearBoard(){
    	for(Component currPB : mainPanel.getComponents()){
    		currPB.setBackground(mainPanel.getBackground());
    	}
    }

}
