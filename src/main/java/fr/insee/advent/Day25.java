package fr.insee.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.insee.advent.Day25.State.Action;
import fr.insee.advent.Day25.State.Action.Way;

public class Day25 {
	
	public static void main(String[] args) throws IOException {
		Day25 day = new Day25();
		System.out.println("Day25");
		System.out.println("1. " + day.ex1(12_964_419 , "src/main/resources/input25"));
	}
	
	public long ex1(long steps, String path) throws IOException {
		Map<String, State> states = this.states(path);
		Tape tape = new Tape();
		State state = states.get("A");
		for (long n = 0; n < steps; n ++) {
			Action action = state.nextAction(tape.isOn()); 
			tape.apply(action);
			state = states.get(action.nextState);
		}
		return tape.checksum();
	}
	
	Map<String, State> states(String path) throws IOException {
		Map<String, State> states = new HashMap<>();
		List<String> lines = Files.readAllLines(Paths.get(path));
		for (int n = 0; n < lines.size(); n = n + 10) {
			State state = new State();
			state.name = lines.get(n).substring(9, 10);
			
			state.offAction = new Action();
			state.offAction.value = lines.get(n + 2).substring(22, 23).equals("1");
			state.offAction.way = lines.get(n + 3).substring(27, 28).equals("r") ? Way.RIGHT : Way.LEFT; 
			state.offAction.nextState = lines.get(n + 4).substring(26, 27);
			
			state.onAction = new Action();
			state.onAction.value = lines.get(n + 6).substring(22, 23).equals("1");
			state.onAction.way = lines.get(n + 7).substring(27, 28).equals("r") ? Way.RIGHT : Way.LEFT; 
			state.onAction.nextState = lines.get(n + 8).substring(26, 27); 
			
			states.put(state.name, state);
		}
		return states;
	}
	
	static class Tape {
		boolean[] values = new boolean[]{ false };
		int index = 0;
		
		boolean isOn() {
			return values[index];
		}
		
		long checksum() {
			int n = 0;
			for (int i = 0; i < values.length; i++) {
				n = n + (values[i] ? 1 : 0);
			}
			return n;
		}
		
		void apply(Action action) {
			values[index] = action.value;
			
			switch(action.way) {
    			case LEFT:
    				index --;
    				if(index == -1) {
    					index = 0;
    					boolean[] newValues = new boolean[values.length + 1];
    					for (int n = 0; n < values.length; n ++) {
    						newValues[n + 1] = values[n];
    					}
    					values = newValues;
    				}
    				break;
    			case RIGHT:
    				index ++;
    				if(index == values.length){
    					boolean[] newValues = new boolean[values.length + 1];
        				for (int n = 0; n < values.length; n ++) {
        					newValues[n] = values[n];
        				}
        				values = newValues;
    				}
    				break;
			}
		}
	}
	
	static class State {
		String name;
		Action onAction, offAction;
		
		Action nextAction(boolean on) {
			return on ? onAction : offAction; 
		}
		
		static class Action {
			boolean value;
			Way way;
			String nextState;
			
			enum Way {
				LEFT, RIGHT
			}
		}
	}
}
