# minecraft.demomod

[Adminy Commands]

To manually register the populated item address (you'll have to grab this from the console)
These are the commands to do 

/registergold 0x000000
/registersilver 0x00000
/registerhost http:localhost:1337/v1/

[Demo Commands]

/register 0x00000000 <-- Wallet Address you want items spawned into
/unregister <-- You don't need this but it lets you re-register your address in case you fuck up
/wallet <-- Shows your current Address

[Items to Spawn]

LootSafe Safe <Looks like an enderchest>
Spawn LootSafe Boss Skeleton - Drops Gold Once.... 
Spawn LootSafe Boss Spider - Drops Silver Once....

!!!!!!!!!WALKTHROUGH!!!!!!!!!!!

1. Place the LootSafe Safe.
2. Use /register and register a valid account address from gnache.
3. Use /registergold and register a valid address for the gold token. 
3. Spawn LootSafe Boss Skeleton.
4. Kill LootSafe Boss Skeleton, notice it pops an gold coin into your inventory.
5. Open the LootSafe Safe, item should disappear, make a call to the API and message you that it was sucessful.
