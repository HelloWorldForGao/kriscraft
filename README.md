# KrisCraft
## Introduction
This mod makes moss far more functional in Minecraft!
It adds new items, gameplay mechanics, crafting recipes, advancements, configuration UI, and custom commands centered around moss.[^0]

## New Items
- Moss Bed: Sleep without needing to harvest wool from sheep.
- Moss Bread: Grants higher hunger restoration and saturation, plus a unique moss mob effect.
- Moss Burger: Provides even greater hunger and saturation, and a dedicated burger mob effect.

## New Game Mechanics
- Raw moss blocks can be consumed instantly with no eating animation, without disrupting normal moss block placement behavior.
- You may also eat moss blocks placed directly on the ground.

## Crafting Recipes
- 3 Moss Blocks + 3 Any Planks (shaped recipe) → Moss Bed[^1]
- 1 Moss Block + 8 Bread (shapeless recipe) → 8 Moss Bread
- 2 Moss Bread + 1 Any Meat (raw or cooked) → Moss Burger[^2]

## New Advancements
- Unlock the "KrisCraft" advancement by eating moss.
- Extra advancements are awarded for sleeping in a Moss Bed, eating Moss Bread, and eating a Moss Burger respectively.

## Configuration UI
- A dedicated config screen accessible via hotkey or ModMenu integration.
- Navigation style inspired by Deltarune:[^3]
    - Up / Down arrow keys to move the selection cursor
    - Left / Right arrow keys to adjust values
- Press Right Shift to save your changes and sync them to the server (requires server operator permissions).
- Press ESC to save changes only to your local client config without syncing to the server.

## Custom Commands
- `/kriscraft config <name> <value>`: Debug-only command. Regular players and server operators can ignore this.
- `/proceed`: High-risk command; use at your own risk.

## Configuration File
All settings are saved in `gameDir/config/kriscraft.json`.
You can edit config values via `/kriscraft config <name> <value>`; tab completion for config keys is not yet implemented.
- `can_eat_moss`: Toggles the ability to eat moss items. Only works when enabled and you are not holding Shift.
- `can_eat_moss_block`: Toggles the ability to eat placed moss blocks. Only works when enabled and you are not holding Shift.
- `moss_heal`: Base health restored by consuming moss.
- `moss_product_heal_base_scale`: Healing multiplier for moss food items.
  The health restored per tick is calculated as:
  `moss_heal * moss_product_heal_base_scale * 0.1`[^4]
  Moss Burger applies a 2x multiplier to this value.

## Acknowledgements
- Deltarune: Original inspiration for the config UI design
- JetBrains: IntelliJ IDEA IDE and Kotlin language support
- Zheng fanghao: Contribution of "Zhi Zhanzhishang (The sacrifice of stopping wars)"
- [Namemc Skin](https://namemc.com/skin/c5e8883a20c16801): Mod icon artwork[^5]

[^0]: This README was rewritten by AI before the release of the v1.0,if you have the AI and you can't understand this README,you can ask AI with the README-CN.MD

[^1]: The recipe layout follows standard shaped crafting rules; check your in-game recipe book if you are unsure.

[^2]: Any raw or cooked meat variant works for this recipe.

[^3]: "Soul" refers to the UI selection cursor within the config screen.

[^4]: Moss Burger doubles the calculated healing amount.

[^5]: Now I am making the icon by myself