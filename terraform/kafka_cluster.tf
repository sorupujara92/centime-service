resource "google_managed_kafka_cluster" "kafka_cluster" {
  cluster_id = "cluster"
  location = "${var.region}"
  capacity_config {
    vcpu_count = 3
    memory_bytes = 3221225472
  }
  project = "${var.project}"

  gcp_config {
    access_config {
      network_configs {
        subnet = "projects/samad-450009/regions/us-east1/subnetworks/default"
      }
    }
  }
  rebalance_config {
    mode = "NO_REBALANCE"
  }
  labels = {
    key = "value"
  }
}

resource "google_managed_kafka_topic" "example" {
  topic_id = "my-topic"
  project = "${var.project}"
  cluster = google_managed_kafka_cluster.kafka_cluster.cluster_id
  location = "${var.region}"
  partition_count = 2
  replication_factor = 3
  configs = {
    "cleanup.policy" = "compact"
  }
}
