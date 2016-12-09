import java.util.Random;

import com.noah.raincloud.serialization.RCArray;
import com.noah.raincloud.serialization.RCField;
import com.noah.raincloud.serialization.RCObject;
import com.noah.raincloud.serialization.RCString;

public class Main {

	static Random random = new Random();

	static void printHex(int value) {
		System.out.printf("0x%x\n", value);
	}

	static void printBin(int value) {
		System.out.println(Integer.toBinaryString(value));
	}

	static void printBytes(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.printf("0x%x ", data[i]);
		}
	}

	public static void serializationTest() {

		float floatData[] = new float[10];
		for (int i = 0; i < 10; i++) {
			floatData[i] = random.nextFloat();
		}
		double doubleData[] = new double[10];
		for (int i = 0; i < 10; i++) {
			doubleData[i] = random.nextDouble();
		}

		RCDatabase database = new RCDatabase("game");

		RCObject playerObject = new RCObject("player");
		RCField playerX = RCField.Integer("xpos", 32);
		RCField playerY = RCField.Integer("ypos", 14);
		RCString playerName = RCString.Create("name", "Noah");
		RCArray playerArray = RCArray.Float("floatArray", floatData);

		playerObject.addField(playerX);
		playerObject.addField(playerY);
		playerObject.addString(playerName);
		playerObject.addArray(playerArray);

		RCObject goblinObject = new RCObject("goblin");
		RCField goblinX = RCField.Integer("xpos", 32);
		RCField goblinY = RCField.Integer("ypos", 14);
		RCString goblinName = RCString.Create("name", "Kleeborp");
		RCArray goblinArray = RCArray.Double("doubleArray", doubleData);

		goblinObject.addField(goblinX);
		goblinObject.addField(goblinY);
		goblinObject.addString(goblinName);
		goblinObject.addArray(goblinArray);

		database.addObject(playerObject);
		database.addObject(goblinObject);
		
		database.serializeToFile("test.rcd");
	}

	public static void deserializationTest() {
		RCDatabase database = RCDatabase.DeserializeFromFile("test.rcd");
		System.out.println("Database: " + database.getName());
		for (RCObject o : database.objects) {
			System.out.println("object name: " + o.getName() + ", " + "size: " + o.getSize());
			for (RCField f : o.fields) {
				System.out.println("\tfield name: " + f.getName());
			}
		}
	}

	public static void main(String[] args) {
//		serializationTest();
//		deserializationTest();
		
		Sandbox sandbox =  new Sandbox();
		sandbox.play();
	}
}