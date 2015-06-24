package presentation.main;

import java.awt.GridLayout;

import presentation.main.SuboptionLayer.SubOptionLabel;

public class MatchSuboptionLayer extends SuboptionLayer{

	public void makeSubOptions() {
		this.setLayout(new GridLayout(2, 1, 2, 2));
		SubOptionLabel teamList = new SubOptionLabel("比赛列表");
		SubOptionLabel teamTable = new SubOptionLabel("比赛直播");
		this.add(teamList);
		this.add(teamTable);
	}
}
