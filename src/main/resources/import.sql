INSERT INTO motivo (descripcion) VALUES ('Retraso en entrega');
INSERT INTO motivo (descripcion) VALUES ('Daños en el producto');
INSERT INTO motivo (descripcion) VALUES ('Pérdida de encomienda');

INSERT INTO reclamos (encomienda_id, motivo_id, descripcion, fecha, estado)
VALUES (1, 1, 'La encomienda llegó tarde', CURRENT_DATE, 'Abierto');
