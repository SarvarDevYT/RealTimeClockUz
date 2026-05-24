# RealTimeClockUz — Ko'p Soatli HUD Mod

**Minecraft Fabric** uchun yengil mod. Real vaqtdagi bir nechta soatni ekranning istalgan burchagida ko'rsatadi.

---

## Xususiyatlar

| Xususiyat | Tavsif |
|-----------|--------|
| 🕐 Ko'p vaqt zonasi | Bir vaqtning o'zida bir nechta shahar vaqtini ko'rsatadi |
| 🏷️ Maxsus nomlar | Har bir soatga o'z nomini bera olasiz (masalan: "Toshkent") |
| 🎨 Har xil ranglar | Har bir soatning matni o'z rangida bo'ladi |
| ⬛ Fon | Matn orqasida shaffof qora fon (o'chib/yonib turadigan) |
| ⏱️ Sekundlar | Sekundlarni ko'rsatish/yashirish |
| 🕛 Format | 12 soatlik yoki 24 soatlik format |
| 💡 Miltillovchi nuqta | Ikki nuqta har 0.5 soniyada miltillaydi |
| 📐 Burchak va oraliq | 4 burchakdan biri, X/Y oraliq sozlanadi |
| 🔴 Yoq/O'chir | Butun HUD ko'rsatkichini bir tugma bilan o'chirib/yoqib qo'yish |

---

## O'rnatish

1. [Fabric Loader](https://fabricmc.net/use/) o'rnating (1.20.1+)
2. [Fabric API](https://modrinth.com/mod/fabric-api) ni `mods/` papkasiga qo'ying
3. [ModMenu](https://modrinth.com/mod/modmenu) ni `mods/` papkasiga qo'ying
4. [Cloth Config](https://modrinth.com/mod/cloth-config) ni `mods/` papkasiga qo'ying
5. `RealTimeClockUz-*.jar` faylini `mods/` papkasiga qo'ying

---

## Sozlamalar

**ModMenu** orqali kirish: `Mods` → `RealTimeClockUz` → `Sozlamalar`.

### Umumiy
- **HUD ni Yoqish** — ko'rsatkichni to'liq yoqish/o'chirish
- **Soniyalarni ko'rsatish** — soatda soniyani ko'rsatish
- **24 soatlik format** — 12h yoki 24h
- **Miltillovchi ikki nuqta** — raqamli soat effekti
- **Fon** — shaffof qora fon

### HUD Holati
- **Burchak** — yuqori chap/o'ng, pastki chap/o'ng
- **X Oraliq** — gorizontal chekkadan masofa
- **Y Oraliq** — vertikal chekkadan masofa

### Vaqt Zonalari
Har bir soat uchun:
- **Nom** — HUD da ko'rinadigan yorliq
- **Vaqt zonasi** — masalan, `Asia/Tashkent`, `Europe/London`

---

## Standart sozlamalar (`config/realtimeclock.json`)

```json
{
  "clocks": [
    { "label": "Tashkent", "zoneId": "Asia/Tashkent", "color": 16777215 },
    { "label": "London",   "zoneId": "Europe/London",  "color": 11194879 },
    { "label": "Tokyo",    "zoneId": "Asia/Tokyo",      "color": 16763050 }
  ],
  "enabled": true,
  "showSeconds": true,
  "use24h": true,
  "showBackground": true,
  "blinkColon": false,
  "offsetX": 6,
  "offsetY": 6,
  "corner": "BOTTOM_RIGHT"
}
```

---

## Tillar

- 🇺🇿 O'zbekcha (`uz_uz`)
- 🇷🇺 Ruscha (`ru_ru`)
- 🇬🇧 Inglizcha (`en_us`)

---

JAR fayli `build/libs/` papkasida hosil bo'ladi.

---

## Litsenziya

MIT — bepul foydalaning, o'zgartiring, tarqating.
