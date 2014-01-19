#-------------======СОЗДАНИЕ ТРИГГЕРОВ ДЛЯ РАБОТЫ ТАРИФНЫХ СЕТОК=======--------------------

DELIMITER |
#При создании тарифной сетки - копируем все тарифы из основной
CREATE TRIGGER `create_ts` AFTER INSERT ON `tariff_scale`
FOR EACH ROW 
BEGIN
   INSERT INTO tariff (tariff_scale_id, city_id, calculation_id, type)
        SELECT NEW.id, city_id, calculation_id, type
          FROM tariff
          JOIN tariff_scale ON(tariff.tariff_scale_id = tariff_scale.id)
        WHERE tariff_scale.main = TRUE;
END |

#При удалении тарифной сетки - удаляем все ее тарифы
CREATE TRIGGER `delete_ts` BEFORE DELETE ON `tariff_scale`
FOR EACH ROW BEGIN
  DELETE FROM tariff WHERE tariff_scale_id=OLD.id;
END |

#При создании города - копируем все тарифы из основной для ПЕРВОГО города
CREATE TRIGGER `create_city` AFTER INSERT ON `cities`
FOR EACH ROW BEGIN
   INSERT INTO tariff (city_id, tariff_scale_id, calculation_id, type)
        SELECT NEW.id, tariff_scale_id, calculation_id, type
          FROM tariff
          JOIN
          (
            SELECT DISTINCT city_id
              FROM tariff
              GROUP BY city_id
              ORDER BY id DESC
              LIMIT 1
          ) AS uniqcity USING (city_id)
        WHERE tariff_scale_id IN (SELECT id FROM tariff_scale WHERE main=TRUE);
END |

#При удалении города - удаляем все его тарифы
CREATE TRIGGER `delete_city` BEFORE DELETE ON `cities`
FOR EACH ROW BEGIN
  DELETE FROM tariff WHERE city_id=OLD.id;
END;

