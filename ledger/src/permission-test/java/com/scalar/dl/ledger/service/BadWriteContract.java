package com.scalar.dl.ledger.service;

import static com.scalar.dl.ledger.service.LedgerServicePermissionTest.AMOUNT_ATTRIBUTE_NAME;
import static com.scalar.dl.ledger.service.LedgerServicePermissionTest.ASSET_ATTRIBUTE_NAME;
import static com.scalar.dl.ledger.service.LedgerServicePermissionTest.BALANCE_ATTRIBUTE_NAME;

import com.scalar.dl.ledger.contract.Contract;
import com.scalar.dl.ledger.database.Ledger;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import javax.json.Json;
import javax.json.JsonObject;

public class BadWriteContract extends Contract {

  @Override
  public JsonObject invoke(Ledger ledger, JsonObject argument, Optional<JsonObject> properties) {
    JsonObject json =
        Json.createObjectBuilder()
            .add(BALANCE_ATTRIBUTE_NAME, argument.getInt(AMOUNT_ATTRIBUTE_NAME))
            .build();
    String assetId = argument.getString(ASSET_ATTRIBUTE_NAME);

    ledger.put(assetId, json);
    ledger.get(assetId);

    try (BufferedWriter bw =
        Files.newBufferedWriter(Paths.get("myFile.txt"), StandardCharsets.UTF_8)) {
      bw.write("some string");
    } catch (IOException e) {
      // ignore since it's a permission test
    }

    return null;
  }
}
