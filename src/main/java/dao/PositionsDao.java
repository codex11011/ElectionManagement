package dao;

import java.util.ArrayList;
import java.util.Iterator;

import models.Candidate;
import models.Position;
import utils.ElectionConfig;

public class PositionsDao {
	public static ArrayList<Position> getApprovedPositionList(){
		ArrayList<Position> positions = ElectionConfig.getPositions();
		ArrayList<Candidate> candidates = CandidateDao.getApprovedCandidates();
		Iterator<Candidate> it = candidates.iterator();
		while (it.hasNext()) {
			Candidate can1 = it.next();
			System.out.println(can1.toString());
		}
		it = candidates.iterator();
		while (it.hasNext()) {
			Candidate can = it.next();
			String regPos = can.getPosition();
			for (int j = 0; j < positions.size(); j++) {
				if (positions.get(j).getName().matches(regPos)) {
					Position postemp = positions.get(j);
					postemp.addCandidate(can);
					positions.set(j, postemp);
				}
			}
		}
		int size = positions.size() - 1;
		for (int i = size; i >= 0; i--) {
			if (positions.get(i).getCandidates().size() == 0) {
				positions.remove(i);
			}
		}
		return positions;
	}
}
