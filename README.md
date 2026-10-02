# KrisCraft

This mod expands moss gameplay in Minecraft.
It adds new items, mechanics, recipes, advancements, a config screen, and custom commands, all themed around moss.[^0]

## New Items
- Moss Bed: Sleep without sheep wool.
- Moss Bread: Good hunger & saturation, gives moss effect.
- Moss Burger: Even better hunger & saturation, gives burger effect.

## Game Mechanics
- You can eat held moss blocks instantly, no eating animation. Placing moss still works normally.
- You can also eat moss blocks that are already placed on the ground.

## Crafting
- 3 Moss Blocks + 3 any planks (shaped) → Moss Bed[^1]
- 1 Moss Block + 8 Bread (shapeless) → 8 Moss Bread
- 2 Moss Bread + 1 any meat (shapeless) → Moss Burger[^2]

## Advancements
- Eat moss → unlock "KrisCraft" advancement.
- Extra advancements for sleeping in Moss Bed, eating Moss Bread, eating Moss Burger.

## Config Screen
Open with hotkey or Mod Menu. UI layout inspired by Deltarune.[^3]
- Up / Down arrow keys: move "soul"(cursor)
- Left / Right arrow keys: change values
- Right Shift: save and sync config to server (needs OP)
- ESC: save only local client settings, do not sync to server.

This menu fits my 1440x900 screen at all,but on other screen it may occur some bugs.

I have no ability to fix this bug now.

If you encounter this bug,you can change the GUI Scale or change one screen.

## Commands
- `/kriscraft data get`: Get your KrisCraft data,like how many moss you have ate.
- `/kriscraft data del`: Delete your KrisCraft data.
- `/kriscraft data server_data`: Get the KrisCraft config on server.
- `/proceed`: Reference to Deltarune Snowgrave. Deadly. Try at your own risk.

## Config File
File location: `config/kriscraft.json`
Use the ui to change it in-game.

Settings list:
- Can eat moss: Allow eating moss. Does nothing while holding Shift.
- Moss heal: Base health restored when eating moss.[^3.1415926]
- Moss products' healing scale: Heal multiplier for moss food.
  Tick‑heal formula:
  `Moss heal` * `Moss products' healing scale` * 0.1[^4]
  > Moss Burger multiplies final result by 2.
- Proceed: Deadly.

## Credits
- Deltarune — where the idea from
- JetBrains — IntelliJ IDEA & Kotlin
- Zheng fanghao — work "Zhi Zhanzhishang (The sacrifice of stopping wars)"
- [Namemc Skin](https://namemc.com/skin/c5e8883a20c16801): Mod icon artwork

[^0]: Early‑version README was rewritten by AI; some parts were hard for me to understand. If English is confusing, read `README‑CN.md`.

[^1]: Check in‑game recipe book for exact shaped pattern.

[^2]: Accepts any raw or cooked meat.

[^3]: Soul means the selection cursor in config UI.

[^3.1415926]: What happens if you set this number negative?

[^4]: Moss Burger x2 on top of calculated value.
