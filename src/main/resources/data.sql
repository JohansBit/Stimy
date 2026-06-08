-- ==========================================
-- 1. INSERTAR USUARIOS (Actualizado con tus campos reales)
-- ==========================================
INSERT IGNORE INTO usuarios (id, nombre_usuario, fecha_nacimiento, nacionalidad)
VALUES (1, 'Juan_Gamer99', '1999-05-14', 'Chilena');

INSERT IGNORE INTO usuarios (id, nombre_usuario, fecha_nacimiento, nacionalidad)
VALUES (2, 'Maria_Pro', '2001-08-22', 'Argentina');

-- ==========================================
-- 2. INSERTAR CATEGORÍAS
-- ==========================================
INSERT IGNORE INTO categorias (id, nombre) VALUES (1, 'Acción');
INSERT IGNORE INTO categorias (id, nombre) VALUES (2, 'RPG');
INSERT IGNORE INTO categorias (id, nombre) VALUES (3, 'Aventura');
INSERT IGNORE INTO categorias (id, nombre) VALUES (4, 'Deportes');
INSERT IGNORE INTO categorias (id, nombre) VALUES (5, 'Estrategia');

-- ==========================================
-- 3. INSERTAR VIDEOJUEGOS
-- ==========================================
-- Juegos de Acción
INSERT IGNORE INTO videojuegos (id, titulo, precio, desarrolladora, anio_salida, categoria_id) VALUES (1, 'Grand Theft Auto V', 29.99, 'Rockstar Games', 2013, 1);
INSERT IGNORE INTO videojuegos (id, titulo, precio, desarrolladora, anio_salida, categoria_id) VALUES (2, 'Doom Eternal', 39.99, 'id Software', 2020, 1);

-- Juegos de RPG
INSERT IGNORE INTO videojuegos (id, titulo, precio, desarrolladora, anio_salida, categoria_id) VALUES (3, 'The Witcher 3: Wild Hunt', 39.99, 'CD Projekt Red', 2015, 2);
INSERT IGNORE INTO videojuegos (id, titulo, precio, desarrolladora, anio_salida, categoria_id) VALUES (4, 'Elden Ring', 59.99, 'FromSoftware', 2022, 2);

-- Juegos de Aventura
INSERT IGNORE INTO videojuegos (id, titulo, precio, desarrolladora, anio_salida, categoria_id) VALUES (5, 'The Legend of Zelda', 59.99, 'Nintendo', 2017, 3);

-- ==========================================
-- 4. INSERTAR LISTA DE DESEOS
-- ==========================================
INSERT IGNORE INTO lista_deseos (id, usuario_id, videojuego_id, fecha_agregado) VALUES (1, 1, 1, '2023-10-15');
INSERT IGNORE INTO lista_deseos (id, usuario_id, videojuego_id, fecha_agregado) VALUES (2, 1, 3, '2023-10-16');
INSERT IGNORE INTO lista_deseos (id, usuario_id, videojuego_id, fecha_agregado) VALUES (3, 2, 4, '2023-10-20');
INSERT IGNORE INTO lista_deseos (id, usuario_id, videojuego_id, fecha_agregado) VALUES (4, 2, 5, '2023-10-21');

-- ==========================================
-- 5. INSERTAR ITEMS EN EL CARRITO
-- ==========================================
INSERT IGNORE INTO items_carrito (id, usuario_id, videojuego_id, precio) VALUES (1, 1, 1, 29.99);
INSERT IGNORE INTO items_carrito (id, usuario_id, videojuego_id, precio) VALUES (2, 1, 2, 39.99);
INSERT IGNORE INTO items_carrito (id, usuario_id, videojuego_id, precio) VALUES (3, 2, 3, 39.99);

-- ==========================================
-- 6. INSERTAR LOGROS
-- ==========================================
INSERT IGNORE INTO logros (id, nombre, descripcion, puntos_recompensa, videojuego_id) VALUES (1, 'Bienvenido a Los Santos', 'Completa la primera misión del juego.', 10, 1);
INSERT IGNORE INTO logros (id, nombre, descripcion, puntos_recompensa, videojuego_id) VALUES (2, 'Oro macizo, amigo', 'Gana 70 medallas de oro en misiones de historia.', 50, 1);
INSERT IGNORE INTO logros (id, nombre, descripcion, puntos_recompensa, videojuego_id) VALUES (3, 'Rip and Tear', 'Mata a tu primer demonio.', 5, 2);
INSERT IGNORE INTO logros (id, nombre, descripcion, puntos_recompensa, videojuego_id) VALUES (4, 'Carnicero de Blaviken', 'Mata al menos a 5 rivales en menos de 10 segundos.', 20, 3);
INSERT IGNORE INTO logros (id, nombre, descripcion, puntos_recompensa, videojuego_id) VALUES (5, 'Reyerta callejera', 'Derrota a un oponente en una pelea a puñetazos sin recibir daño.', 15, 3);

-- ==========================================
-- 7. INSERTAR RESEÑAS
-- ==========================================
INSERT IGNORE INTO resenias (id, calificacion, comentario, fecha_publicacion, videojuego_id, usuario_id) VALUES (1, 5, 'Una obra maestra absoluta, nunca me canso de jugarlo.', '2023-11-01', 1, 1);
INSERT IGNORE INTO resenias (id, calificacion, comentario, fecha_publicacion, videojuego_id, usuario_id) VALUES (2, 4, 'Mucha acción, pero un poco repetitivo al final.', '2023-11-05', 2, 1);
INSERT IGNORE INTO resenias (id, calificacion, comentario, fecha_publicacion, videojuego_id, usuario_id) VALUES (3, 5, 'El mejor juego que he jugado en mi vida. Muy difícil pero satisfactorio.', '2023-11-10', 4, 2);

-- ==========================================
-- 8. INSERTAR TICKETS DE SOPORTE
-- ==========================================
INSERT IGNORE INTO tickets_soporte (id, usuario_id, asunto, descripcion, estado, fecha_creacion)
VALUES (1, 1, 'Error de compra', 'Intento comprar el Doom Eternal pero mi tarjeta es rechazada.', 'ABIERTO', '2023-11-15 10:30:00');

INSERT IGNORE INTO tickets_soporte (id, usuario_id, asunto, descripcion, estado, fecha_creacion)
VALUES (2, 2, 'Juego no aparece', 'Compré Elden Ring ayer y aún no aparece disponible.', 'EN_PROCESO', '2023-11-18 14:45:00');

-- ==========================================
-- 9. INSERTAR AMIGOS
-- ==========================================
-- El usuario 1 (Juan) y la usuaria 2 (Maria) son amigos
INSERT IGNORE INTO amigos (id, usuario_id, amigo_id, estado) VALUES (1, 1, 2, 'ACEPTADO');
INSERT IGNORE INTO amigos (id, usuario_id, amigo_id, estado) VALUES (2, 2, 1, 'ACEPTADO');

-- ============================================
-- 10. INSERTAR PAGOS
--=============================================
INSERT INTO Pagos (usuarioId, montoTotal, estado, fechaPago) VALUES (1, 15000, 'COMPLETADO', '2026-06-01 14:30:00');
INSERT INTO Pagos (usuarioId, montoTotal, estado, fechaPago) VALUES (1, 35000, 'PENDIENTE', '2026-06-02 10:15:00');
INSERT INTO Pagos (usuarioId, montoTotal, estado, fechaPago) VALUES (1, 5000, 'RECHAZADO', '2026-06-03 18:45:00');
INSERT INTO Pagos (usuarioId, montoTotal, estado, fechaPago) VALUES (2, 60000, 'COMPLETADO', '2026-06-05 09:20:00');
INSERT INTO Pagos (usuarioId, montoTotal, estado, fechaPago) VALUES (2, 22500, 'REEMBOLSADO', '2026-06-06 16:00:00');

-- ============================================
-- 11. INSERTAR BIBLIOTECA
-- ============================================
INSERT INTO biblioteca (usuarioId, videojuegoId, fechaCompra) VALUES (1, 1, '2026-06-01 14:35:00');
INSERT INTO biblioteca (usuarioId, videojuegoId, fechaCompra) VALUES (1, 3, '2026-06-01 14:35:00');
INSERT INTO biblioteca (usuarioId, videojuegoId, fechaCompra) VALUES (1, 2, '2026-06-02 10:20:00');
INSERT INTO biblioteca (usuarioId, videojuegoId, fechaCompra) VALUES (2, 1, '2026-06-05 09:25:00');
INSERT INTO biblioteca (usuarioId, videojuegoId, fechaCompra) VALUES (2, 5, '2026-06-05 18:00:00');