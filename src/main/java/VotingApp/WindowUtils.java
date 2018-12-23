package VotingApp;

import java.awt.event.WindowAdapter;

import javax.swing.JFrame;

public class WindowUtils {

	public static void waitTillComplete(final JFrame frame) throws InterruptedException {
		final Object lock = new Object();
		Thread t = new Thread() {
			public void run() {
				synchronized (lock) {
					while (frame.isVisible())
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					// System.out.println("Working now");
				}
			}
		};
		t.start();

		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				synchronized (lock) {
					frame.setVisible(false);
					lock.notify();
				}
			}

		});

		t.join();
	}
}