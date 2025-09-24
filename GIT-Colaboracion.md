# 🤝 Guía de Colaboración - Rama `dev`

Este documento explica **cómo trabajar en el proyecto sin romper nada**.  
La idea es que todos los cambios se hagan en la rama `dev`, y **nunca en `main`**.

---

## ✅ Primeros pasos 

1. **Revisar cambios locales**

```
git status
```
- Si ves archivos en **rojo**, hacé:

  ```
  git add archivo.java
  git commit -m "mensaje sobre tus cambios"
  ```

- Repetí `git status` hasta que diga:

  ```
  nothing to commit, working tree clean
  ```

2. **Verificar que el remoto esté configurado**

```
git remote -v
```

- Si aparece la URL del repo → seguí.
- Si no aparece nada:

  ```
  git remote add origin <URL-del-repo>
  ```

3. **Traer ramas del remoto**

```
git fetch
```

4. **Ver las ramas disponibles**

```
git branch -a
```

- Deberías ver algo como:
    - `main`
    - `remotes/origin/main`
    - `remotes/origin/dev`

5. **Pasarse a la rama `dev`**

```
git switch dev
```

- Si todavía no existe localmente:

  ```
  git switch -c dev origin/dev
  ```

---

## 🚀 Flujo de trabajo diario en `dev`

Mejor seguir este orden:

1. **Actualizar la rama antes de trabajar**

```
git pull
```

2. **Guardar cambios**

```
git add .
git commit -m "mensaje de lo que hiciste"
```


3. **Subir cambios al remoto**

```
git push
```
---

## 📝 Ejemplos de mensajes de commit

✅ Recomendado:  
- `agrego lógica de colisión de disparos`  
- `corrijo bug en movimiento del enemigo`  
- `refactorizo clase Tanque para usar herencia`  

❌ No recomendado:  
- `cambio cosas`  
- `arreglo`  
- `update`  

---
## 🔑 Tips importantes

- **NO trabajes en `main`.**
- Los comandos que más vas a usar son:
```
git add .
git commit -m "mensaje"
git push
```


## 🆘 Qué hacer si trabajaste en `main` por error

1. Guardá tus cambios:
```
git add .
git commit -m "mover cambios a dev"
```
2. Cambiate a la rama `dev`:
```
git switch dev
```

3. Traé tus commits de `main` a `dev`:

```
git merge main
```
4. Subirlos al remoto en la rama correcta (cuando estes conforme con los cambios):
```
git push origin dev

```