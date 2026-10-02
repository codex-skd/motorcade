# Changelog — Motorcade (NeoForge)

## [0.0.0-beta.1] - Unreleased

- Initial port of Automobility (FoundationGames) to NeoForge 1.21.1 / 21.1.249, based on the
  author's own unreleased 1.21.1 NeoForge rewrite.
- Fixed broken translation keys, missing datagen output (prefab vehicles, slope models), and
  lang capitalization issues found during in-game testing.
- Added Spanish (es_es) translation.
- Added an in-game guide book (requires Vellumli): 4 categories, 14 entries, in English and
  Spanish, covering the build loop, automobile parts, roads/terrain, and driving controls.
- Removed the UTF-8 BOM from the `en_us` and `zh_cn` lang files: with the byte-order mark
  present both parsed as invalid JSON. Text unchanged; the `es_es` file had none.
