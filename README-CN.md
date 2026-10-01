# KrisCraft

这个模组拓展Minecraft里苔藓的玩法。
围绕苔藓增加新物品、游戏机制、合成配方、成就、配置界面与自定义命令。

## 新增物品
- 苔藓床：不用羊毛也可以睡觉
- 苔藓面包：不错的饥饿与饱和度，附加苔藓效果
- 苔藓汉堡：更高饥饿、饱和度，附加汉堡效果

## 游戏机制
- 手持苔藓方块可以直接瞬间吃掉，没有进食动画；放置方块功能不受影响。
- 已经放在地上的苔藓方块也可以直接食用。

## 合成配方
- 3苔藓块 + 3任意木板（有序）→ 苔藓床[^1]
- 1苔藓块 + 8面包（无序）→ 8个苔藓面包
- 2苔藓面包 + 1任意肉类（无序）→ 苔藓汉堡[^2]

## 成就
- 吃下苔藓解锁「Kris工艺」成就
- 使用苔藓床睡觉、吃苔藓面包、吃苔藓汉堡各有对应成就

## 配置界面
快捷键或ModMenu打开，UI风格参考Deltarune。[^3]
- ↑↓方向键：移动光标
- ←→方向键：修改数值
- 右Shift：保存并同步配置到服务端（需要OP权限）
- ESC：仅保存本地客户端，不同步服务器

## 命令
- `/kriscraft config <name> <value>`：调试专用，普通玩家不用管
- `/proceed`：致敬Deltarune雪葬(Snowgrave)，它是致命的，请自行承担后果

## 配置文件
路径：`config/kriscraft.json`
游戏内可以用 `/kriscraft config <name> <value>` 修改，暂不支持Tab补全。

配置项：
- `can_eat_moss`：允许食用苔藓物品，按住Shift无效
- `can_eat_moss_block`：允许食用地上的苔藓方块，按住Shift无效
- `moss_heal`：吃苔藓回复的基础生命值[^3.1415926]
- `moss_product_heal_base_scale`：苔藓食物治疗倍率
  每刻治疗计算公式：
  `moss_heal * moss_product_heal_base_scale * 0.1`[^4]
  > 苔藓汉堡会在此结果再乘以2

## 致谢
- Deltarune — 配置界面灵感，`/proceed` 对应雪葬路线
- JetBrains — IntelliJ IDEA、Kotlin语言
- Zheng fanghao —《止战之殇》相关贡献
- [Namemc Skin](https://namemc.com/skin/c5e8883a20c16801)：旧版模组图标素材

[^1]: 有序合成具体样式看游戏内配方书
[^2]: 生肉、熟肉都可以参与合成
[^3]: 灵魂就是配置界面的选择光标
[^3.1415926]: 如果把这个数值改成负数会发生什么？
[^4]: 苔藓汉堡在计算结果基础再乘2
