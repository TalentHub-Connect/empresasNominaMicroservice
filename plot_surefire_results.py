import os
import xml.etree.ElementTree as ET
import matplotlib.pyplot as plt

# Directorio donde se encuentran los archivos de resultados de Surefire
surefire_reports_dir = './target/surefire-reports/'

# Inicializar contadores
total_tests = 0
total_failures = 0
total_errors = 0
total_skipped = 0

# Iterar sobre los archivos de resultados
for filename in os.listdir(surefire_reports_dir):
    if filename.endswith('.xml'):
        filepath = os.path.join(surefire_reports_dir, filename)
        tree = ET.parse(filepath)
        root = tree.getroot()

        # Actualizar contadores
        total_tests += int(root.attrib.get('tests', 0))
        total_failures += int(root.attrib.get('failures', 0))
        total_errors += int(root.attrib.get('errors', 0))
        total_skipped += int(root.attrib.get('skipped', 0))

# Crear la gráfica
labels = ['Tests', 'Failures', 'Errors', 'Skipped']
values = [total_tests, total_failures, total_errors, total_skipped]
colors = ['green', 'red', 'orange', 'blue']

plt.figure(figsize=(10, 6))
bars = plt.bar(labels, values, color=colors)

# Añadir los valores encima de las barras
for bar in bars:
    yval = bar.get_height()
    plt.text(bar.get_x() + bar.get_width() / 2.0, yval, int(yval), va='bottom', ha='center')

plt.title('Unit Test Results Nómina')
plt.ylabel('Number of Tests')
plt.xlabel('Test Categories')
plt.ylim(0, max(values) + 5)

plt.show()
