package com.arisosoftware.rsautil;

import org.iq80.leveldb.DB;
import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

import java.io.File;
import java.io.IOException;

import org.iq80.leveldb.Options;

public class LevelDBDAO {

	public void Init() throws IOException {
		DB levelDBStore;
		Options options = new Options();
		levelDBStore = factory.open(new File("levelDBStore"), options);

		// # base

		levelDBStore.put("key".getBytes(), "value".getBytes());
		// levelDBStore.
	}
}
