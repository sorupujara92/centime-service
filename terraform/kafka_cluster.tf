resource "google_managed_kafka_cluster" "kafka_cluster" {
  cluster_id = "poc_kafka_cluster"
  location = "us-central1"
  capacity_config {
    vcpu_count = 1
    memory_bytes = 3221225472
  }
  project = "samad-450009"

  gcp_config {
    access_config {
      network_configs {
        subnet = "projects/samad-450009/regions/us-central1/subnetworks/default"
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
  cluster = google_managed_kafka_cluster.kafka_cluster.cluster_id
  location = "us-central1"
  partition_count = 2
  replication_factor = 3
  configs = {
    "cleanup.policy" = "compact"
  }
}
