package org.nit.jobgrpcservice.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.nit.jobapp.JobRequest;
import org.nit.jobapp.JobResponse;
import org.nit.jobapp.JobServiceGrpc;
import org.nit.jobgrpcservice.model.JobPost;
import org.nit.jobgrpcservice.service.JobService;

@GrpcService
public class JobGrpcService extends JobServiceGrpc.JobServiceImplBase {

    private final JobService jobService;

    public JobGrpcService(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void getJob(JobRequest request,
                       StreamObserver<JobResponse> responseObserver) {

        JobPost job = jobService.getJob(request.getPostId());

        JobResponse response =
                JobResponse.newBuilder()
                        .setPostId(job.getPostId())
                        .setProfile(job.getPostProfile())
                        .setDescription(job.getPostDesc())
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}