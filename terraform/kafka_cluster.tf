resource "google_managed_kafka_cluster" "kafka_cluster" {
  cluster_id = "poc_kafka_cluster"
  location = "us-central1"
  capacity_config {
    vcpu_count = 1
    memory_bytes = 3221225472
  }
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

# data "google_project" "project" {
# }