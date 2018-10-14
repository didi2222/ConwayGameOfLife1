import java.util.*;

class Cell {

	List<Integer> pos;

	boolean[] isLive = new boolean[2];

	int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

	// boolean[] hasLiveNeighbor = new boolean[2];

	public Cell(List<Integer> pos, int time, boolean isLive) {
		this.pos = pos;
		this.isLive[time] = isLive;
	}

	public void update(Map<List<Integer>, Cell> map, int time) {
			int count = 0;
			for (int[] dir : dirs) {
				int x = pos.get(0) + dir[0], y = pos.get(1) + dir[1];
				List<Integer> neighbor = Arrays.asList(x, y);
				if (map.containsKey(neighbor) && (map.get(neighbor).isLive[1 - time])) {
					count++;
				}
			}
			if ((isLive[1-time] && count == 2) || count == 3) {
				isLive[time] = true;
			}else
				isLive[time]=false;
	}

}

public class ConwayGameOfLife {
	Map<List<Integer>, Cell> map = new HashMap<>();
	int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };
	int time = 0;
	
	public ConwayGameOfLife(int[][] lives) {
		for (int[] live : lives) {
			Cell cell = new Cell(Arrays.asList(live[0], live[1]), time, true);
			map.put(cell.pos, cell);
		}
	}

	public void AddCellsToMap() {
		Map<List<Integer>, Cell> m = new HashMap<>();
		for (Cell cell : map.values()) {
			for (int[] dir : dirs) {
				int x = dir[0] + cell.pos.get(0), y = dir[0] + cell.pos.get(1);
				List<Integer> list = Arrays.asList(x, y);
				if (map.containsKey(list) || m.containsKey(list))
					continue;
				Cell new_cell = new Cell(Arrays.asList(x, y), time, false);
				m.put(new_cell.pos, new_cell);
			}
		}
		map.putAll(m);
	}

	public void RemoveCells() {
		Iterator<Map.Entry<List<Integer>, Cell>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			if (!it.next().getValue().isLive[time])
				it.remove();
		}
	}

	public void Update() {
		AddCellsToMap();
		time = 1 - time;
		for (Cell cell : map.values()) {
			cell.update(map, time);
		}
		RemoveCells();
		Iterator<Map.Entry<List<Integer>, Cell>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			System.out.println(it.next().getKey().toString());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		int[][] lives = { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 }, { 2, 2 } };

		ConwayGameOfLife cgl = new ConwayGameOfLife(lives);

		cgl.Update();
		cgl.Update();
		
		// System.out.println(res);

	}

}