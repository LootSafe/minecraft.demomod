# minecraft.demomod

## Admin Commands - These only work for the OPS/ADMINS not regular players.

To manually register the populated item address (you'll have to grab this from the console)
These are the commands to do 

* **/registergold coinaddress**
* **/registerhost hostaddress**
* **/registerport hostport**
  
### Debug  

Wipes players progress on what boss they killed and loot they recieved. This is for debugging so you can kill the boss multiple time.  
  
* **/wipeplayer playername**

Save the current state of the players loot token inventory to file

* **/lootsave**

## Demo Commands

* **/register playerwallet** 
* **/unregister** 
* **/walletstatus** 
* **/lootsafehelp** 
* **/blank** 

## Unused at the moment

* **/loot** <-- Shows id's received.
* **/registersilver coinaddress**

## Items to Spawn

* LootSafe Safe - Looks like an enderchest
* Spawn LootSafe Boss Skeleton **- Drops Gold Once...** 
* Spawn LootSafe Boss Spider **- Drops Silver Once...**

### WALKTHROUGH

1. Place the LootSafe Safe.
2. Use **/register** and register a valid account address from gnache.
3. Use **/registergold** and register a valid address for the gold token. 
3. Spawn LootSafe Boss Skeleton.
4. Kill LootSafe Boss Skeleton, notice it pops an gold coin into your inventory.
5. Open the LootSafe Safe, item should disappear, make a call to the API and message you that it was successful.
