package fr.insee.advent;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Jour13 {

	public static void main(String[] args) throws Exception {
		Jour13 jour = new Jour13();
		System.out.println("Jour13");
		System.out.println("1. " + jour.ex1("src/main/resources/input13"));
		System.out.println("2. " + jour.ex2("src/main/resources/input13"));
	}
	
	public long ex1(String path) throws Exception {
		Firewall firewall = new Firewall(path);
		for (int n = 0; n < 100; n ++) {
			firewall.nextStep();
		}
		return firewall.severity;
	}
	
	public long ex2(String path) throws Exception {
		Map<Integer, Layer> layers = Files.readAllLines(Paths.get(path))
			.stream()
			.map(Layer::fromLine)
			.collect(Collectors.toMap(layer -> layer.depth, Function.identity()));
		int delay = 10;
		while (true) {
			boolean caught = false;
			for (int pico = 0; pico < 100; pico ++) {
				Layer layer = layers.get(pico);
				if (layer != null) {
					if ((pico + delay) % (2 * layer.range - 2) == 0) {
						caught = true;
						break;
					}
				}
			}
			if (!caught) {
				return delay;
			}
			delay ++;
		}
	}

	private static class Layer {
		
		int depth, range, scanner;
		boolean up;

		private Layer(int depth, int range) {
			super();
			this.depth = depth;
			this.range = range;
			this.scanner = 0;
			this.up = false;
		}
		
		public static Layer fromLine(String line) {
			line = line.replaceAll(" +", "");
			String[] tokens = line.split(":");
			int depth = Integer.valueOf(tokens[0]);
			int range = Integer.valueOf(tokens[1]);
			return new Layer(depth, range);
		}
		
		public void scanNext() {
			if (up) {
				scanner --;
			}
			else {
				scanner ++;
			}
			if (scanner == 0 || scanner == range - 1) {
				up = !up;
			}
		}
		
		public int severity(int packet) {
			if (packet == this.depth && this.scanner == 0) {
				return this.depth * this.range;
			}
			return 0;
		}
	}
	
	private static class Firewall {
		
		Map<Integer, Layer> layers;
		int packet, severity;
		
		public Firewall(String path) throws Exception {
			this.packet = 0;
			this.severity = 0;
			this.layers = Files.readAllLines(Paths.get(path))
				.stream()
				.map(Layer::fromLine)
				.collect(Collectors.toMap(layer -> layer.depth, Function.identity()));
		}
		
		public void nextStep() {
			Layer layer = layers.get(packet);
			if (layer != null) {
				severity = severity + layer.severity(packet);
			}
			packet ++;
			layers.values()
				.stream()
				.forEach(Layer::scanNext);
		}
	}
}
