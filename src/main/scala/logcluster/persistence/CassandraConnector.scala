package logcluster.persistence

import com.datastax.driver.core.Cluster
import org.joda.time.DateTime
import com.typesafe.scalalogging.StrictLogging

// cassandra.hosts = ["shamedb-cass-01"]

object CassandraConnector {
  val cluster = Cluster.builder()
    .addContactPoints("shamedb-cass-01.despexds.net")
    .build()
  cluster.getConfiguration().getSocketOptions().setReadTimeoutMillis(60000)
  val session = cluster.connect()
}

object ErrorPersister extends StrictLogging {

  def persist(application: String, cluster: String, entry: String) = {
    val timestamp = new DateTime

    logger.debug("Persist in Cass")
  }
}

/*
CREATE TABLE app_errors (
  application text,
  date_from timestamp, // TTL
  error_count int,
  PRIMARY KEY((id), date_from));

CREATE TABLE clusters (
  application text,
  cluster_id text,
  date_from timestamp,
  error text,
  error_count int,
  PRIMARY KEY((application), date_from, cluster_id));

CREATE TABLE cluster_similars (
  application text,
  cluster_id text,
  similars set<text>,
  PRIMARY KEY(application, cluster_id)
);

insert into clusters (application, cluster_id, date_from, error, error_count) values ('cloudia', 'cluster-1', '2015-10-30 17:50', 'Err: NPE', 5);
insert into clusters (application, cluster_id, date_from, error, error_count) values ('cloudia', 'cluster-2', '2015-10-30 17:50', 'Err: SOE', 3);
insert into clusters (application, cluster_id, date_from, error, error_count) values ('cloudia', 'cluster-1', '2015-10-30 17:51', 'Err: NPE', 1);
insert into clusters (application, cluster_id, date_from, error, error_count) values ('cloudia', 'cluster-1', '2015-10-30 17:52', 'Err: NPE', 15);
insert into clusters (application, cluster_id, date_from, error, error_count) values ('hrm', 'cluster-1', '2015-10-30 17:50', 'Err: NPE-hrm', 235);

insert into app_errors (application, date_from, error_count) values ('cloudia', '2015-10-30 17:50', 8);
insert into app_errors (application, date_from, error_count) values ('cloudia', '2015-10-30 17:51',1);
insert into app_errors (application, date_from, error_count) values ('cloudia', '2015-10-30 17:52',15);
insert into app_errors (application, date_from, error_count) values ('cloudia', '2015-10-30 17:50',235);

Application Histogram (min->err_count):
select date_from, error_count from clusters where application = 'cloudia' and date_from > '2015-10-30 17:50';

Errores por cluster (al resultado lo tengo q sumar a mano)
select cluster_id, error, error_count from clusters where application = 'cloudia' and date_from > '2015-10-30 17:50';

Errores por app (al resultado lo tengo q sumar a mano)
select application, error_count from app_errors where date_from > '2015-10-30 17:50' ALLOW FILTERING;

Ejemplos de errores
select similars from cluster_similars where application = 'cloudia' and cluster_id = 'cluster-1';

*
*/
