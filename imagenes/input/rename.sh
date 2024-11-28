#!/bin/bash

# Directorio donde están los archivos (puedes cambiarlo)
DIRECTORIO="./"

# Extensión de los archivos a renombrar (puedes cambiarla)
EXTENSION="jpg"

# Contador para numerar los archivos
CONTADOR=1

# Cambiar al directorio especificado
cd "$DIRECTORIO" || { echo "No se pudo acceder al directorio $DIRECTORIO"; exit 1; }

# Iterar sobre los archivos con la extensión especificada
for ARCHIVO in *.$EXTENSION; do
    # Verifica si existen archivos con la extensión
    if [[ -e "$ARCHIVO" ]]; then
        # Construir el nuevo nombre del archivo
        NUEVO_NOMBRE="img${CONTADOR}.$EXTENSION"

        # Renombrar el archivo
        mv "$ARCHIVO" "$NUEVO_NOMBRE"

        # Incrementar el contador
        ((CONTADOR++))
    fi
done

echo "Renombrado completado. Total de archivos: $((CONTADOR-1))"

