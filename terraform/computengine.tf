resource "google_compute_instance" "vm_instance_project_a" {
  name         = "terraform-instance"
  machine_type = "e2-micro"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-11"
    }
  }

  network_interface {
    network = "default"
     access_config {
   }

  }


  metadata = {
    enable-oslogin = "true"
  }

  metadata_startup_script = <<-EOF
  #!/bin/bash
  host_ip=$(curl ifconfig.me)
  awx_url='https://awx.z-apps.io/api/v2/job_templates/22/launch/'
  awx_token=''
  data="{\"limit\": \"$host_ip\"}"
  curl -X POST "$awx_url" -H "Authorization: Bearer $awx_token" -H "Content-Type: application/json" -d "$data"
  EOF
  zone="us-central1-a"
  project="gcp-bootcamp-410606"
}

