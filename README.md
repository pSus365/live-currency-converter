
# Currency Converter (Console, Async, SOLID, Java 17)

A clean, student‑friendly implementation based on the provided UML.  
It downloads NBP's latest table A (`LastA.xml`) **asynchronously** using `HttpClient`, parses it, and offers a small **console UI**.

## Run (Maven)

```bash
mvn -q -e -DskipTests exec:java
```

> Requires Java 17+ and Maven.

## Architecture (short)

- **repo**: `RemoteRepository` (port) and `RestRepository` (adapter) — async HTTP download.
- **codec**: `Encoder` and `ISOEncoder` — bytes → text (ISO‑8859‑2 fallback to UTF‑8).
- **parser**: `Document` and `XMLDocument` — parse NBP XML to domain model.
- **model**: `ExchangeTable`, `ExchangeRate` — aggregate root + value object.
- **service**: `Exchange` — domain conversion via PLN.
- **console**: Command pattern (`DisplayTable`, `ExchangeCurrency`, `Exit`).
- **app**: `ExchangeApp` — singleton orchestrator, exposes `getTableAsync()`.

SOLID-friendly:
- Interfaces at boundaries (S, O, D). Small classes with single responsibilities (S).  
- `ExchangeApp` composes behavior; no static utilities (I).

## Notes

- NBP's XML uses comma decimal separator and sometimes a multiplier (`przelicznik`); code normalizes to `.` and divides by multiplier to obtain *PLN per unit*.
- Conversion formula: `amount * r1(PLN) / r2(PLN)`.
- Table A does not include PLN itself.
