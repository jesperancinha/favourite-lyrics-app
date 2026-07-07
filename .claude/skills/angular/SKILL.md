---
name: angular
description: Conventions for Angular applications 
---

## 1. In all setup files, the target should be esnext

### Example in `tsconfig.json`:

Replace: 

```json
{
  "compilerOptions": {
    "target": "es5",
    "lib": ["es5", "dom"],
    "types": ["cypress", "node"]
  },
  "include": ["**/*.ts"]
}
```

with 

```json
{
  "compilerOptions": {
    "target": "esnext",
    "lib": ["esnext", "dom"],
    "types": ["cypress", "node"]
  },
  "include": ["**/*.ts"]
}
```

## 2. Checklist

[] All targets in `tsconfig.json` files should be set to `esnext`
[] No target should remain with old target compiler option versions
