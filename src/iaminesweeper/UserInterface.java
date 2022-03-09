package iaminesweeper;

import collections.LinkedList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;

import java.awt.Desktop;

/**
 *
 * @author m.rowles
 */
public class UserInterface extends javax.swing.JFrame {
    
    GameEngine engine;
    Dimension screenSize;
    
    Color backgroundColor = new Color(195, 187, 184);

    /**
     * Creates new form UserInterface
     */
    public UserInterface() {
        screenSize = new Dimension(162, 204);
        initComponents();
        
        LinkedList<JLabel> timerLabels = new LinkedList<>();
        timerLabels.add(timerLblOne);
        timerLabels.add(timerLblTen);
        timerLabels.add(timerLblHundred);
        LinkedList<JLabel> flagLabels = new LinkedList<>();
        flagLabels.add(flagLblOne);
        flagLabels.add(flagLblTen);
        flagLabels.add(flagLblHundred);
        
        engine = new GameEngine(statusLbl, gamePnl,
                                timerLabels,flagLabels,this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gridBtnGrp = new javax.swing.ButtonGroup();
        menuPopup = new javax.swing.JPopupMenu();
        graphItem = new javax.swing.JMenuItem();
        jSeparator = new javax.swing.JPopupMenu.Separator();
        easyMenuRdBtn = new javax.swing.JRadioButtonMenuItem();
        medMenuRdBtn = new javax.swing.JRadioButtonMenuItem();
        hardMenuRdBtn = new javax.swing.JRadioButtonMenuItem();
        bgPnl = new javax.swing.JPanel();
        dataPnl = new javax.swing.JPanel();
        timerLblHundred = new javax.swing.JLabel();
        timerLblTen = new javax.swing.JLabel();
        timerLblOne = new javax.swing.JLabel();
        flagLblHundred = new javax.swing.JLabel();
        flagLblTen = new javax.swing.JLabel();
        flagLblOne = new javax.swing.JLabel();
        statusLbl = new javax.swing.JLabel();
        gamePnl = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        menuGame = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();

        menuPopup.setBackground(javax.swing.UIManager.getDefaults().getColor("Menu.foreground"));
        menuPopup.setForeground(javax.swing.UIManager.getDefaults().getColor("Menu.background"));

        graphItem.setText("<html>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; &nbsp; Height&ensp; Width&ensp; Mines</html>");
        graphItem.setToolTipText("");
        graphItem.setContentAreaFilled(false);
        graphItem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        graphItem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        graphItem.setRequestFocusEnabled(false);
        graphItem.setVerifyInputWhenFocusTarget(false);
        menuPopup.add(graphItem);
        menuPopup.add(jSeparator);

        easyMenuRdBtn.setBackground(javax.swing.UIManager.getDefaults().getColor("MenuItem.foreground"));
        gridBtnGrp.add(easyMenuRdBtn);
        easyMenuRdBtn.setForeground(javax.swing.UIManager.getDefaults().getColor("MenuItem.background"));
        easyMenuRdBtn.setSelected(true);
        easyMenuRdBtn.setText("<html><b>Beginner:</b> &nbsp;&emsp;&emsp; 9 &emsp;&emsp;&ensp; 9 &emsp;&emsp;&ensp; 10</html>");
        menuPopup.add(easyMenuRdBtn);

        medMenuRdBtn.setBackground(javax.swing.UIManager.getDefaults().getColor("MenuItem.foreground"));
        gridBtnGrp.add(medMenuRdBtn);
        medMenuRdBtn.setForeground(javax.swing.UIManager.getDefaults().getColor("MenuItem.background"));
        medMenuRdBtn.setText("<html><b>Intermediate:</b>&nbsp;&ensp; 16 &emsp;&emsp;16 &emsp;&emsp;&nbsp; 40</html>");
        menuPopup.add(medMenuRdBtn);

        hardMenuRdBtn.setBackground(javax.swing.UIManager.getDefaults().getColor("MenuItem.foreground"));
        gridBtnGrp.add(hardMenuRdBtn);
        hardMenuRdBtn.setForeground(javax.swing.UIManager.getDefaults().getColor("MenuItem.background"));
        hardMenuRdBtn.setText("<html><b>Expert:</b> &nbsp;&emsp;&emsp;&emsp; 16 &emsp;&emsp; 30 &emsp;&emsp; 99</html>");
        menuPopup.add(hardMenuRdBtn);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setName("minesweeper"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(162, 204));

        bgPnl.setBackground(new java.awt.Color(189, 189, 189));
        bgPnl.setPreferredSize(screenSize);

        dataPnl.setBackground(new java.awt.Color(189, 189, 189));
        dataPnl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        dataPnl.setLayout(null);

        timerLblHundred.setBackground(new java.awt.Color(0, 0, 0));
        timerLblHundred.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        timerLblHundred.setForeground(new java.awt.Color(255, 51, 51));
        timerLblHundred.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLblHundred.setText("0");
        timerLblHundred.setToolTipText("");
        timerLblHundred.setAlignmentY(0.0F);
        timerLblHundred.setMaximumSize(new java.awt.Dimension(13, 23));
        timerLblHundred.setMinimumSize(new java.awt.Dimension(13, 23));
        timerLblHundred.setOpaque(true);
        timerLblHundred.setPreferredSize(new java.awt.Dimension(13, 23));
        dataPnl.add(timerLblHundred);
        timerLblHundred.setBounds(14, 12, 13, 23);

        timerLblTen.setBackground(new java.awt.Color(0, 0, 0));
        timerLblTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        timerLblTen.setForeground(new java.awt.Color(255, 51, 51));
        timerLblTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLblTen.setText("0");
        timerLblTen.setToolTipText("");
        timerLblTen.setMaximumSize(new java.awt.Dimension(13, 23));
        timerLblTen.setMinimumSize(new java.awt.Dimension(13, 23));
        timerLblTen.setOpaque(true);
        timerLblTen.setPreferredSize(new java.awt.Dimension(13, 23));
        dataPnl.add(timerLblTen);
        timerLblTen.setBounds(27, 12, 13, 23);

        timerLblOne.setBackground(new java.awt.Color(0, 0, 0));
        timerLblOne.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        timerLblOne.setForeground(new java.awt.Color(255, 51, 51));
        timerLblOne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLblOne.setText("0");
        timerLblOne.setToolTipText("");
        timerLblOne.setMaximumSize(new java.awt.Dimension(13, 23));
        timerLblOne.setMinimumSize(new java.awt.Dimension(13, 23));
        timerLblOne.setOpaque(true);
        timerLblOne.setPreferredSize(new java.awt.Dimension(13, 23));
        dataPnl.add(timerLblOne);
        timerLblOne.setBounds(40, 12, 13, 23);

        flagLblHundred.setBackground(new java.awt.Color(0, 0, 0));
        flagLblHundred.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flagLblHundred.setForeground(new java.awt.Color(255, 51, 51));
        flagLblHundred.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        flagLblHundred.setText("0");
        flagLblHundred.setToolTipText("");
        flagLblHundred.setMaximumSize(new java.awt.Dimension(13, 23));
        flagLblHundred.setMinimumSize(new java.awt.Dimension(13, 23));
        flagLblHundred.setOpaque(true);
        flagLblHundred.setPreferredSize(new java.awt.Dimension(13, 23));
        dataPnl.add(flagLblHundred);
        flagLblHundred.setBounds(107, 12, 13, 23);

        flagLblTen.setBackground(new java.awt.Color(0, 0, 0));
        flagLblTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flagLblTen.setForeground(new java.awt.Color(255, 51, 51));
        flagLblTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        flagLblTen.setText("0");
        flagLblTen.setToolTipText("");
        flagLblTen.setMaximumSize(new java.awt.Dimension(13, 23));
        flagLblTen.setMinimumSize(new java.awt.Dimension(13, 23));
        flagLblTen.setOpaque(true);
        flagLblTen.setPreferredSize(new java.awt.Dimension(13, 23));
        dataPnl.add(flagLblTen);
        flagLblTen.setBounds(120, 12, 13, 23);

        flagLblOne.setBackground(new java.awt.Color(0, 0, 0));
        flagLblOne.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        flagLblOne.setForeground(new java.awt.Color(255, 51, 51));
        flagLblOne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        flagLblOne.setText("0");
        flagLblOne.setToolTipText("");
        flagLblOne.setMaximumSize(new java.awt.Dimension(13, 23));
        flagLblOne.setMinimumSize(new java.awt.Dimension(13, 23));
        flagLblOne.setOpaque(true);
        flagLblOne.setPreferredSize(new java.awt.Dimension(13, 23));
        dataPnl.add(flagLblOne);
        flagLblOne.setBounds(133, 12, 13, 23);

        statusLbl.setBackground(new java.awt.Color(187, 187, 187));
        statusLbl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLbl.setToolTipText("");
        statusLbl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        statusLbl.setMaximumSize(new java.awt.Dimension(26, 26));
        statusLbl.setMinimumSize(new java.awt.Dimension(26, 26));
        statusLbl.setOpaque(true);
        statusLbl.setPreferredSize(new java.awt.Dimension(26, 26));
        statusLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                statusLblMouseEvent(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                statusLblMouseEvent(evt);
            }
        });
        dataPnl.add(statusLbl);
        statusLbl.setBounds(67, 11, 26, 26);

        gamePnl.setBackground(new java.awt.Color(189, 189, 189));
        gamePnl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        gamePnl.setForeground(new java.awt.Color(189, 189, 189));
        gamePnl.setMinimumSize(new java.awt.Dimension(148, 140));
        gamePnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gamePnlMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout gamePnlLayout = new javax.swing.GroupLayout(gamePnl);
        gamePnl.setLayout(gamePnlLayout);
        gamePnlLayout.setHorizontalGroup(
            gamePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
        );
        gamePnlLayout.setVerticalGroup(
            gamePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bgPnlLayout = new javax.swing.GroupLayout(bgPnl);
        bgPnl.setLayout(bgPnlLayout);
        bgPnlLayout.setHorizontalGroup(
            bgPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgPnlLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(bgPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gamePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgPnlLayout.setVerticalGroup(
            bgPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgPnlLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(dataPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(gamePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar.setBackground(javax.swing.UIManager.getDefaults().getColor("EditorTab.selectedForeground"));
        jMenuBar.setForeground(javax.swing.UIManager.getDefaults().getColor("text"));

        menuGame.setText("Game");
        gridBtnGrp.add(menuGame);
        menuGame.setComponentPopupMenu(menuPopup);
        menuGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuGameMouseClicked(evt);
            }
        });
        jMenuBar.add(menuGame);

        menuHelp.setText("Help");
        menuHelp.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuHelpMenuSelected(evt);
            }
        });
        jMenuBar.add(menuHelp);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void statusLblMouseEvent(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusLblMouseEvent
        // TODO add your handling code here:
        engine.mouseClick(evt, "Status");
    }//GEN-LAST:event_statusLblMouseEvent

    private void menuGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuGameMouseClicked
        // TODO add your handling code here:
        menuPopup.show(this, 10, 55);
    }//GEN-LAST:event_menuGameMouseClicked

    private void menuHelpMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuHelpMenuSelected
        // TODO add your handling code here:
        engine.openURI("https://minesweepergame.com/strategy.php");
         
        
    }//GEN-LAST:event_menuHelpMenuSelected

    private void gamePnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gamePnlMouseClicked
        // TODO add your handling code here:
        engine.mouseClick(evt, "Grid");
    }//GEN-LAST:event_gamePnlMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgPnl;
    private javax.swing.JPanel dataPnl;
    private javax.swing.JRadioButtonMenuItem easyMenuRdBtn;
    private javax.swing.JLabel flagLblHundred;
    private javax.swing.JLabel flagLblOne;
    private javax.swing.JLabel flagLblTen;
    private javax.swing.JPanel gamePnl;
    private javax.swing.JMenuItem graphItem;
    private javax.swing.ButtonGroup gridBtnGrp;
    private javax.swing.JRadioButtonMenuItem hardMenuRdBtn;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPopupMenu.Separator jSeparator;
    private javax.swing.JRadioButtonMenuItem medMenuRdBtn;
    private javax.swing.JMenu menuGame;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JPopupMenu menuPopup;
    private javax.swing.JLabel statusLbl;
    private javax.swing.JLabel timerLblHundred;
    private javax.swing.JLabel timerLblOne;
    private javax.swing.JLabel timerLblTen;
    // End of variables declaration//GEN-END:variables
}
